package inventory_dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            if (!categories.isEmpty()) {
                return ResponseEntity.ok(categories);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve categories.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                return ResponseEntity.ok(categoryOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve the category.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category newCategory = categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the category.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            if (categoryRepository.existsById(id)) {
                category.setId(id); 
                Category updatedCategory = categoryRepository.save(category);
                return ResponseEntity.ok(updatedCategory);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the category.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the category.");
        }
    }
}
