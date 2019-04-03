package schoolboy;

import dbwork.address.Address;
import lombok.Data;
import professions.Profession;
import school.School;

import java.util.List;

@Data
public class Schoolboy {

    private int id;

    private int age;

    private Address address;

    private School school;

    private String firstname;

    private String lastname;

    private List<Profession> professions ;

    public void add(Profession profession){
        professions.add(profession);
    }

    public Schoolboy(int id, int age, Address address, School school, String firstname, String lastname, List<Profession> professions) {
        this.id = id;
        this.age = age;
        this.address = address;
        this.school = school;
        this.firstname = firstname;
        this.lastname = lastname;
        this.professions = professions;
    }
}
