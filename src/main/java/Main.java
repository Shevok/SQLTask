

import dbwork.address.Address;
import dbwork.address.AddressCrud;
import professions.Profession;
import professions.ProfessionCrud;
import relations.SchoolboyProfRelation;
import relations.SchoolboyProfRelationCrud;
import school.School;
import school.SchoolCrud;
import schoolboy.Schoolboy;
import schoolboy.SchoolboyCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{

    private final String url = "jdbc:postgresql://127.0.0.1:5432/parkDB";
    private final String user = "postgres";
    private final String password = "root";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        Main app = new Main();
        Connection con = app.connect();
        final int CURRENT_INDEX = 13;//я не сделал автоиндексацию,поэтому эта переменная эта текущий индекс
        try {

           AddressCrud addressCrud = new AddressCrud();
           ProfessionCrud professionCrud = new ProfessionCrud();
           SchoolboyProfRelationCrud schoolboyProfRelationCrud = new SchoolboyProfRelationCrud();
           SchoolCrud schoolCrud = new SchoolCrud();
           SchoolboyCrud schoolboyCrud = new SchoolboyCrud();
            List <Profession> professions = new ArrayList<>();
            professions.add(professionCrud.read(1,con));
            professions.add(professionCrud.read(2,con));

            List <Profession> newProfessions = new ArrayList<>();
            newProfessions.add(professionCrud.read(3,con));
            newProfessions.add(professionCrud.read(4,con));

            /**
             *create new schoolboy test
             */

           /*schoolboyCrud.create(new Schoolboy(CURRENT_INDEX,17
                   ,new Address(CURRENT_INDEX, "Минск", "Беды" ,4)
                   ,new School(CURRENT_INDEX,15,"Средняя школа")
                   ,"Григорий","Алферов",professions),con);*/
            /**
             * update schoolboy test
             */
           /*schoolboyCrud.update(new Schoolboy(CURRENT_INDEX,12
                    ,new Address(CURRENT_INDEX, "Брест", "Беды" ,4)
                    ,new School(CURRENT_INDEX,12,"Средняя школа")
                    ,"Ян","Алферов",professions), newProfessions , con);*/
            /**
             * read schoolboy test
             */
            //Schoolboy testMan = schoolboyCrud.read(CURRENT_INDEX, con);
            /**
             * delete schoolboy test
             */
            //schoolboyCrud.delete(CURRENT_INDEX,con);

           con.close();
        }catch (Exception e){
            System.out.println(e+"Main");
        }
    }
}