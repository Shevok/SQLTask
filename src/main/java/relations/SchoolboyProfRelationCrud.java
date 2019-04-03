package relations;

import dbwork.address.Address;
import org.postgresql.util.PSQLException;
import professions.Profession;
import professions.ProfessionCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Profession> read (int id, Connection con){

        List<Profession> result = new ArrayList<>();
        ProfessionCrud professionCrud = new ProfessionCrud();
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT profession_id FROM schoolboy_profession_relation" +
                            " WHERE schoolboy_id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            int i = 1;
            while (rs.next()){
                result.add(professionCrud.read(rs.getInt(i),con));
                }
                return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update (int idSchoolboy, int newProfessionsId, int oldProfessionsId, Connection con){
        try {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE schoolboy_profession_relation\n" +
                            "SET profession_id = ?\n" +
                            "WHERE schoolboy_id = ? AND profession_id = ?");

            ps.setInt(1, newProfessionsId );
            ps.setInt(2, idSchoolboy);
            ps.setInt(3, oldProfessionsId);

            ps.executeUpdate();

        } catch (PSQLException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idSchoolboy, int idProfession, Connection con){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    " DELETE FROM schoolboy_profession_relation WHERE schoolboy_id = ? AND profession_id = ?");

            ps.setInt(1, idSchoolboy);
            ps.setInt(2, idProfession);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
