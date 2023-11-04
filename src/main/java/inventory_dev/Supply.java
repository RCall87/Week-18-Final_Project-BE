package inventory_dev;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Supply")
@IdClass(SupplyId.class) // Specify the IdClass
public class Supply implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // Constructors
    public Supply() {
    }

    public Supply(Product product, Supplier supplier) {
        this.product = product;
        this.supplier = supplier;
    }

    // Getters and setters for product and supplier
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
