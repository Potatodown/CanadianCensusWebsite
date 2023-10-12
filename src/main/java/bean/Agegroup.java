package bean;

import jakarta.persistence.*;

@Entity
@Table(name = "agegroup")
public class Agegroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ageGroupID;

    private String description;

    public int getAgeGroupID() {
        return ageGroupID;
    }

    public void setAgeGroupID(int ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
