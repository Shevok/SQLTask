package schoolboy;

import dbwork.address.AddressCrud;
import org.postgresql.util.PSQLException;
import professions.Profession;
import relations.SchoolboyProfRelation;
import relations.SchoolboyProfRelationCrud;
import school.SchoolCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SchoolboyCrud {

    public void create(Schoolboy schoolboy, Connection con){

        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO schoolboy (id_schoolboy, age, address_id, school_id, firstname, lastname)" +
                            " VALUES (?, ?, ?, ?, ?, ?)");

            AddressCrud addressCrud = new AddressCrud();
            addressCrud.create(schoolboy.getAddress(),con);

            SchoolCrud schoolCrud = new SchoolCrud();
            if(schoolCrud.read(schoolboy.getSchool().getId(),con)==null)
                schoolCrud.create(schoolboy.getSchool(),con);


            ps.setInt(1, schoolboy.getId());
            ps.setInt(2, schoolboy.getAge());
            ps.setInt(3, schoolboy.getAddress().getId());
            ps.setInt(4, schoolboy.getSchool().getId());
            ps.setString(5,schoolboy.getFirstname());
            ps.setString(6,schoolboy.getLastname());
            ps.execute();

            SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();
            for (Profession prof:schoolboy.getProfessions() ) {
                schoolboyProfRelationCrud.create(new SchoolboyProfRelation(schoolboy.getId(),prof.getId()),con);
            }



        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Schoolboy read (int id, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT age, address_id, school_id, firstname, lastname FROM schoolboy" +
                            " WHERE id_schoolboy = ? ");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            AddressCrud addressCrud = new AddressCrud();
            SchoolCrud schoolCrud = new SchoolCrud();
            SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();

            if (rs.next())
                //System.out.println("Result set :" + rs.getInt(1));
            return new Schoolboy(id,rs.getInt(1),addressCrud.read(rs.getInt(2),con)
                    , schoolCrud.read(rs.getInt(3),con),rs.getString(4)
                    , rs.getString(5), schoolboyProfRelationCrud.read(id,con));
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (Schoolboy schoolboy, List<Profession> newProfessions, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE schoolboy\n" +
                            "SET age = ?, address_id = ?, school_id = ?, firstname = ?, lastname = ? \n" +
                            "WHERE id_schoolboy = ?");

            ps.setInt(1, schoolboy.getAge());
            ps.setInt(2, schoolboy.getAddress().getId());
            ps.setInt(3, schoolboy.getSchool().getId());
            ps.setString(4, schoolboy.getFirstname());
            ps.setString(5, schoolboy.getLastname());
            ps.setInt(6, schoolboy.getId());


            AddressCrud addressCrud = new AddressCrud();
            SchoolCrud schoolCrud = new SchoolCrud();
            SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();
            addressCrud.update(schoolboy.getAddress(),con);
            schoolCrud.update(schoolboy.getSchool(),con);
            for(int i = 0; i < schoolboy.getProfessions().size(); i++){
                schoolboyProfRelationCrud.update(schoolboy.getId(), newProfessions.get(i).getId()
                        , schoolboy.getProfessions().get(i).getId(), con);
            }
            ps.executeUpdate();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id,Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " DELETE FROM schoolboy WHERE id_schoolboy = ?");
            ps.setInt(1, id);

            SchoolboyCrud schoolboyCrud = new SchoolboyCrud();
            List<Profession> allProfession;
            allProfession = schoolboyCrud.read(id, con).getProfessions();

            AddressCrud addressCrud = new AddressCrud();
            SchoolCrud schoolCrud = new SchoolCrud();
            SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();
            for(int i = 0; i < allProfession.size(); i++ )
                schoolboyProfRelationCrud.delete(id, allProfession.get(i).getId(), con);
            ps.execute();
            addressCrud.delete(id,con);
            schoolCrud.delete(id,con);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
