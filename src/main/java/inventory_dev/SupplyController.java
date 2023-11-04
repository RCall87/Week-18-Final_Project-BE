package inventory_dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {
    private static final Logger log = LoggerFactory.getLogger(SupplyController.class);

    @Autowired
    private SupplyRepository supplyRepository;

    @GetMapping
    public ResponseEntity<?> getAllSupplies() {
        try {
            List<Supply> supplies = supplyRepository.findAll();
            if (!supplies.isEmpty()) {
                return new ResponseEntity<>(supplies, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No supplies found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving supplies: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to retrieve supplies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{productId}/{supplierId}")
    public ResponseEntity<?> getSupplyByIds(@PathVariable Long productId, @PathVariable Long supplierId) {
        try {
            Supply supply = supplyRepository.findByProduct_ProductIdAndSupplier_Id(productId, supplierId);
            if (supply != null) {
                return new ResponseEntity<>(supply, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supply not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving a supply by IDs: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to retrieve the supply", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSupply(@RequestBody Supply supply) {
        try {
            Supply newSupply = supplyRepository.save(supply);
            return new ResponseEntity<>(newSupply, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An error occurred while creating a supply: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create the supply", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{productId}/{supplierId}")
    public ResponseEntity<?> updateSupply(@PathVariable Long productId, @PathVariable Long supplierId, @RequestBody Supply supply) {
        try {
            boolean exists = supplyRepository.existsByProduct_ProductIdAndSupplier_Id(productId, supplierId);
            if (exists) {
                Supply updatedSupply = supplyRepository.save(supply);
                return new ResponseEntity<>(updatedSupply, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supply not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while updating a supply by IDs: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update the supply", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}/{supplierId}")
    public ResponseEntity<?> deleteSupply(@PathVariable Long productId, @PathVariable Long supplierId) {
        try {
            boolean exists = supplyRepository.existsByProduct_ProductIdAndSupplier_Id(productId, supplierId);
            if (exists) {
                supplyRepository.deleteByProduct_ProductIdAndSupplier_Id(productId, supplierId);
                return new ResponseEntity<>("Supply deleted successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Supply not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while deleting a supply by IDs: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete the supply", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
