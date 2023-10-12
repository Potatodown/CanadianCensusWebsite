package bean;

import jakarta.persistence.*;

@Entity
@Table(name = "household")
public class Household {


    private int geographicArea;

    private int householdType;

    private int householdSize;

    private int householdByAgeRange;

    private int householdEarners;

    private int totalIncome;

    private int censusYear;

    private int numberReported;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int getGeographicArea() {
        return geographicArea;
    }

    public void setGeographicArea(int geographicArea) {
        this.geographicArea = geographicArea;
    }

    public int getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(int householdType) {
        this.householdType = householdType;
    }

    public int getHouseholdSize() {
        return householdSize;
    }

    public void setHouseholdSize(int householdSize) {
        this.householdSize = householdSize;
    }

    public int getHouseholdByAgeRange() {
        return householdByAgeRange;
    }

    public void setHouseholdByAgeRange(int householdByAgeRange) {
        this.householdByAgeRange = householdByAgeRange;
    }

    public int getHouseholdEarners() {
        return householdEarners;
    }

    public void setHouseholdEarners(int householdEarners) {
        this.householdEarners = householdEarners;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public int getNumberReported() {
        return numberReported;
    }

    public void setNumberReported(int numberReported) {
        this.numberReported = numberReported;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
