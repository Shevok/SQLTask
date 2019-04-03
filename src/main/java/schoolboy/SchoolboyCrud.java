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

    public Address read (int id, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT (id_address, city, street, house_number) FROM address" +
                            " WHERE id_address = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next())
                return new Address(id, rs.getString(2),
                        rs.getString(3), rs.getInt(4));
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (Address address, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE address\n" +
                            "SET city = ?, street = ?, house_number = ?\n" +
                            "WHERE id_address = ?");

            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getHouseNumber());
            ps.setInt(4, address.getId());

            ps.executeUpdate();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id,Connection con){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    " DELETE FROM address WHERE id_address = ?");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
