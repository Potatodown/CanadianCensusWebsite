package bean;

import jakarta.persistence.*;

@Entity
@Table(name = "geographicarea")
public class Geographicarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int geographicAreaID;

    private int code;

    private int level;

    private String name;

    private int alternativeCode;
    public Geographicarea( int code, int level, String name) {
        this.code = code;
        this.level = level;
        this.name = name;
    }
    public int getGeographicAreaID() {
        return geographicAreaID;
    }

    public void setGeographicAreaID(int geographicAreaID) {
        this.geographicAreaID = geographicAreaID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(int alternativeCode) {
        this.alternativeCode = alternativeCode;
    }
}
