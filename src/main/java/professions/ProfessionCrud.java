package professions;

import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessionCrud {

    public void create(Profession profession, Connection con){

        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO profession (id_profession, description)" +
                            " VALUES (?, ?)");
            ps.setInt(1, profession.getId());
            ps.setString(2, profession.getDescription());

            ps.execute();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Profession read (int id, Connection con){

        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT (id_profession, description) FROM profession" +
                            " WHERE id_profession = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new Profession(id, rs.getString(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (Profession profession, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE profession\n" +
                            "SET description = ?\n" +
                            "WHERE id_profession = ?");

            ps.setString(1, profession.getDescription());
            ps.setInt(2, profession.getId());


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
                    " DELETE FROM profession WHERE id_profession = ?");

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
