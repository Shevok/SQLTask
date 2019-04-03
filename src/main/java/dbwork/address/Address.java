package dbwork.address;

import lombok.Data;

@Data
public class Address {

    private int id;

    private String city;

    private String street;

    private int houseNumber;

    public Address() {
    }

    public Address(int id,  String city, String street, int houseNumber) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.city = city;
        this.street = street;
    }
}
