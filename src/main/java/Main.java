

import dbwork.address.Address;
import dbwork.address.AddressCrud;
import professions.Profession;
import professions.ProfessionCrud;
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
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM schoolboy");

            ResultSet rs = ps.executeQuery();
            //получаем следующую строку результата, если она есть. '''В самом начале курсор стоит перед'''
            //'''первой строкой'''. Если строка есть функция next() возвращает true и передвигает курсор
            //на следующую строку
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
            }

           // AddressCrud addressCrud = new AddressCrud();
           // addressCrud.create(new Address(8, "Минск", "Гикало", 10),con);
//          Address address = addressCrud.read(3,con);
//            System.out.println(address.getId() + " "+address.getCity() + " " + address.getStreet()+ " " + address.getHouseNumber());
//           addressCrud.update(new Address(2, "Витебск", "Красивая", 7),con);
            //addressCrud.delete(5,con);

            SchoolCrud schoolCrud = new SchoolCrud();
//            schoolCrud.create(new School(5,5,"Гимназия"),con);
//            School school = schoolCrud.read(3,con);
//            System.out.println(school.getId() + " "+school.getSchoolNumber() + " " + school.getSchoolType());
//            schoolCrud.delete(5,con);
//            schoolCrud.update(new School(1,1,"Гимназия"),con);

            ProfessionCrud professionCrud = new ProfessionCrud();
//            professionCrud.create(new Profession(4,"Военный"),con);
//            professionCrud.delete(4,con);
//            Profession profession = professionCrud.read(1,con);
//            System.out.println(profession.getId() + " "+profession.getDescription());
//            professionCrud.update(new Profession(1,"Стоматолог"),con);

            /*SchoolboyCrud schoolboyCrud = new SchoolboyCrud();
            Schoolboy schoolboy = new Schoolboy(9, 16, new Address(9, "Minsk", "asd", 5)
                    , new School(9, 7, "Gym"), "Egor", "Shevko"
                    , new ArrayList<>());
            schoolboy.add(new Profession(1,"Useless"));
            schoolboy.add(new Profession(2,"bus"));

            schoolboyCrud.create(schoolboy,con);*/
            ps.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}