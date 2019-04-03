package school;

import lombok.Data;

@Data
public class School {

    private int id;

    private int schoolNumber;

    private String schoolType;

    public School() {
    }

    public School(int id, int schoolNumber, String schoolType) {
        this.id = id;
        this.schoolNumber = schoolNumber;
        this.schoolType = schoolType;
    }
}
