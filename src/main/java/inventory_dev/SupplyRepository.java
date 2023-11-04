package inventory_dev;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, SupplyId> {
    
    // Custom query method to check if a supply exists by product and supplier IDs
    boolean existsByProduct_ProductIdAndSupplier_Id(Long productId, Long supplierId);

    // Custom query method to find a supply by product and supplier IDs
    Supply findByProduct_ProductIdAndSupplier_Id(Long productId, Long supplierId);

    // Custom query method to delete a supply by product and supplier IDs
    void deleteByProduct_ProductIdAndSupplier_Id(Long productId, Long supplierId);
}
