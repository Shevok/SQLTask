package schoolboy;

import dbwork.address.Address;
import dbwork.address.AddressCrud;
import org.postgresql.util.PSQLException;
import professions.Profession;
import professions.ProfessionCrud;
import relations.SchoolboyProfRelation;
import relations.SchoolboyProfRelationCrud;
import school.School;
import school.SchoolCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    "SELECT (age, address_id, school_id, firstname, lastname) FROM schoolboy" +
                            " WHERE id_schoolboy = ? ");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            AddressCrud addressCrud = new AddressCrud();
            SchoolCrud schoolCrud = new SchoolCrud();
            SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();

            if (rs.next()){
                System.out.println("Result set :" + rs.getInt(1));
            }

            /*return new Schoolboy(id,rs.getInt(2),addressCrud.read(rs.getInt(3),con)
                    , schoolCrud.read(rs.getInt(4),con),rs.getString(5)
                    , rs.getString(6), schoolboyProfRelationCrud.read(id,con));*/
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (Schoolboy schoolboy, Connection con){
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
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
