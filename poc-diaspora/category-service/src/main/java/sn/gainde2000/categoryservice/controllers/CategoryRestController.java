package sn.gainde2000.categoryservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.categoryservice.entities.Category;
import sn.gainde2000.categoryservice.exceptions.CategoryNotFoundException;
import sn.gainde2000.categoryservice.service.ICategory;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    private ICategory iCategory;

    public CategoryRestController(ICategory iCategory) {
        this.iCategory = iCategory;
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        Category category1= iCategory.addCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listCategory(){
        List<Category> categories = iCategory.listCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id)
            throws CategoryNotFoundException {
        Category category = iCategory.getCategory(id);
        return ResponseEntity.ok().body(category);
    }
}
