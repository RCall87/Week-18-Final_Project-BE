package inventory_dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private static final Logger log = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierRepository.findAll();
            if (!suppliers.isEmpty()) {
                return new ResponseEntity<>(suppliers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No suppliers found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving suppliers: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to retrieve suppliers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        try {
            Optional<Supplier> supplierOptional = supplierRepository.findById(id);
            if (supplierOptional.isPresent()) {
                return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving a supplier by ID: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to retrieve the supplier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier newSupplier = supplierRepository.save(supplier);
            return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An error occurred while creating a supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to create the supplier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        try {
            if (supplierRepository.existsById(id)) {
                supplier.setId(id); // Ensure the ID matches the path variable
                Supplier updatedSupplier = supplierRepository.save(supplier);
                return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while updating a supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update the supplier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        try {
            if (supplierRepository.existsById(id)) {
                supplierRepository.deleteById(id);
                return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("An error occurred while deleting a supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to delete the supplier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
