package inventory_dev; // Make sure the package declaration matches your expected package

import java.io.Serializable;
import java.util.Objects;

public class SupplyId implements Serializable {
    private Long product;
    private Long supplier;
    
    private static final long serialVersionUID = 1L;

    public SupplyId() {
    }

    public SupplyId(Long product, Long supplier) {
        this.product = product;
        this.supplier = supplier;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyId supplyId = (SupplyId) o;
        return Objects.equals(product, supplyId.product) && Objects.equals(supplier, supplyId.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, supplier);
    }
}
