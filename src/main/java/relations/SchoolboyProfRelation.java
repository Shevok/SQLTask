package relations;

import lombok.Data;

@Data
public class SchoolboyProfRelation {

    private int idSchoolboy;

    private int idProfession;

    public SchoolboyProfRelation() {
    }

    public SchoolboyProfRelation(int idSchoolboy, int idProfession) {
        this.idSchoolboy = idSchoolboy;
        this.idProfession = idProfession;
    }
}
