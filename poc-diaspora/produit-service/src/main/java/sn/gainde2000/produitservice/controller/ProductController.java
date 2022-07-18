package sn.gainde2000.produitservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.produitservice.exceptions.ResourceNotFoundException;
import sn.gainde2000.produitservice.models.Product;
import sn.gainde2000.produitservice.services.IProduct;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private IProduct iProduct;

    public ProductController(IProduct iProduct) {
        this.iProduct = iProduct;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        Product prod= iProduct.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> listProduct(){
        List<Product> products = iProduct.listProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {
         Product product = iProduct.getProduct(id);
        return ResponseEntity.ok().body(product);
    }
}
