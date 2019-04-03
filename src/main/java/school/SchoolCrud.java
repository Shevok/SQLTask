package school;

import dbwork.address.Address;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolCrud {

    public void create(School school, Connection con){

        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO school (id_school, school_number, school_type)" +
                            " VALUES (?, ?, ?)");
            ps.setInt(1, school.getId());
            ps.setInt(2, school.getSchoolNumber());
            ps.setString(3, school.getSchoolType());

            ps.execute();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public School read (int id, Connection con){
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT (school_number, school_type) FROM school" +
                            " WHERE id_school = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next())
                return new School(id, rs.getInt(1),
                        rs.getString(2));
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (School school, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE school\n" +
                            "SET school_number = ?, school_type = ?\n" +
                            "WHERE id_school = ?");

            ps.setInt(1, school.getSchoolNumber());
            ps.setString(2, school.getSchoolType());
            ps.setInt(3, school.getId());

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
                    " DELETE FROM school WHERE id_school = ?");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
