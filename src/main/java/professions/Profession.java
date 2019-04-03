package professions;

import lombok.Data;

@Data
public class Profession {

    private int id;

    private String description;

    public Profession() {
    }

    public Profession(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
