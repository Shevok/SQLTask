package relations;

import dbwork.address.Address;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolboyProfRelationCrud {

    public void create(SchoolboyProfRelation schoolboyProfRelation, Connection con){

        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO schoolboy_profession_relation (schoolboy_id, profession_id)" +
                            " VALUES (?, ?)");
            ps.setInt(1, schoolboyProfRelation.getIdSchoolboy());
            ps.setInt(2, schoolboyProfRelation.getIdProfession());

            ps.execute();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Address read (int id, Connection con){
        ResultSet rs = null;
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
