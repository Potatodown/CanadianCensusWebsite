package bean;

import jakarta.persistence.*;

@Entity
@Table(name = "censusyear")
public class CensusYear {


    private int censusYear;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
