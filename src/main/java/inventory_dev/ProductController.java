package inventory_dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if (!products.isEmpty()) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve products.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                return ResponseEntity.ok(productOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve the product.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the product.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            if (productRepository.existsById(id)) {
                product.setId(id);
                Product updatedProduct = productRepository.save(product);
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the product.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the product.");
        }
    }
}
