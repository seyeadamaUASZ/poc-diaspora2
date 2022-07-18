package sn.gainde2000.categoryservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.categoryservice.entities.Category;
import sn.gainde2000.categoryservice.exceptions.CategoryNotFoundException;
import sn.gainde2000.categoryservice.service.ICategory;
import sn.gainde2000.tools.ServeurResponse;

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

//    @GetMapping
//    public ResponseEntity<?> listCategory(){
//        List<Category> categories = iCategory.listCategory();
//        return ResponseEntity.ok(categories);
//    }

    @GetMapping
    public ServeurResponse listCategory(){
        ServeurResponse response = new ServeurResponse();
        List<Category> listCategories = iCategory.listCategory();
        if(!listCategories.isEmpty()){
            response.setStatut(true);
            response.setDescription("liste des categories");
            response.setData(listCategories);
        }else{
            response.setStatut(false);
            response.setDescription("Non disponible");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id)
            throws CategoryNotFoundException {
        Category category = iCategory.getCategory(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/countCategories")
    public ServeurResponse countCategories(){
        ServeurResponse response = new ServeurResponse();
        Integer totalCategories = iCategory.countCategories();
        response.setStatut(true);
        response.setData(totalCategories);
        response.setDescription("total des categories");
        return response;
    }


}
