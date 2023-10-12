import bean.Age;
import bean.Geographicarea;
import bean.Household;
import bean.Totalincome;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;


public class Main {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    //Main Console
    public static void main(String[] args) {
        //findGeoArea10(); <- question 1
        //getGeoAreaslevel2(); <- question 2
        //get10TotalIncome(); <- question 3
        //getSpecificCensus(); <- question 4
        //getSpecificCensus(); <- question 5
        //criteriaQueryA(); <- question 6.A
        //criteriaQueryB(); <- question 6.B (incomplete)

        entityManagerFactory.close();
    }

    public static void findGeoArea10(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Geographicarea area = null;
        try{
           area = entityManager.find(Geographicarea.class, 10);
           System.out.printf("%-2s %-5s %-5s %-5s %-15s %n", "Id", "code", "level", "name", "altcode");
           System.out.printf("%-2s %-5s %-5s %-5s %-5s %n", area.getGeographicAreaID(), area.getCode(), area.getLevel(),area.getName(), area.getAlternativeCode());
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            //Close Entity Manager
            entityManager.close();
        }
    }
    public static void getGeoAreaslevel2() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT a FROM Geographicarea a WHERE a.level = 2";
        TypedQuery<Geographicarea> entityTypedQuery = entityManager.createQuery(query, Geographicarea.class);
        try {
            List<Geographicarea> GeographicareaEntityList = entityTypedQuery.getResultList();
            System.out.printf("%-2s %-5s %-5s %-5s %-15s %n", "Id", "code", "level", "name", "altcode");
            GeographicareaEntityList.forEach(field -> System.out.printf("%-2s %-5s %-5s %-5s %-5s %n", field.getGeographicAreaID(), field.getCode(), field.getLevel(),field.getName(), field.getAlternativeCode()));
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void get10TotalIncome() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Totalincome> entityTypedQuery = entityManager.createNamedQuery("findallIncome", Totalincome.class);
        entityTypedQuery.setMaxResults(10);
        try {
            List<Totalincome> incomeEntityList = entityTypedQuery.getResultList();
            System.out.printf("%-2s %-5s %n", "Id", "Description");
            incomeEntityList.forEach(field -> System.out.printf("%-2s %-5s %n", field.getId(),field.getDescription()));
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void getSpecificCensus(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT a  FROM Household a WHERE a.censusYear = 2 AND a.householdType = 4 AND a.householdSize = 3 AND a.householdEarners = 3 AND a.totalIncome =15";
        TypedQuery<Household> entityTypedQuery = entityManager.createQuery(query, Household.class);
        try {
            List<Household> incomeEntityList = entityTypedQuery.getResultList();
            System.out.println("Total Records: "+incomeEntityList.stream().count());
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void criteriaQueryA(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Geographicarea> cr = cb.createQuery(Geographicarea.class);
        Root<Geographicarea> root = cr.from(Geographicarea.class);
        cr.multiselect(root.get("code"), root.get("level"), root.get("name"));
        try {
            List<Geographicarea> GeographicareaEntityList = entityManager.createQuery(cr).setMaxResults(10).getResultList();
            System.out.printf("%-2s %-5s %-5s  %n","code", "level", "name");
            GeographicareaEntityList.forEach(field -> System.out.printf("%-2s %-5s %-5s %n", field.getCode(), field.getLevel(),field.getName()));
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void criteriaQueryB(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Age> cr = cb.createQuery(Age.class);
        Root<Age> root = cr.from(Age.class);
        cr.orderBy(cb.desc(root.get("ageID")));
        cr.select(root);
        try {
            List<Age> GeographicareaEntityList = entityManager.createQuery(cr).setMaxResults(20).getResultList();
            System.out.printf("%-2s %-5s %-5s %-5s %-5s %-5s %-5s %n","ageID", "ageGroup", "censusYear", "geaographicArea", "combined", "male", "female");
            GeographicareaEntityList.forEach(field -> System.out.printf("%-2s %-5s %-5s %-5s %-5s %-5s %-5s %n", field.getAgeID(), field.getAgeGroup(), field.getCensusYear(), field.getGeographicArea(), field.getCombined(), field.getMale(), field.getFemale()));
        } catch (NoResultException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
