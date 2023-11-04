package inventory_dev;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String contactEmail;

    @ManyToMany
    @JoinTable(name = "Supply",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> suppliedProducts;

    // Constructors
    public Supplier() {
    }

    public Supplier(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    // Getters and setters for id, name, and contactEmail
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Getter and setter for suppliedProducts, if needed
    public List<Product> getSuppliedProducts() {
        return suppliedProducts;
    }

    public void setSuppliedProducts(List<Product> suppliedProducts) {
        this.suppliedProducts = suppliedProducts;
    }
}
