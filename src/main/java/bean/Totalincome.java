package bean;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "findallIncome", query = "SELECT i from Totalincome i")
@Table(name = "totalincome")
public class Totalincome {



    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
