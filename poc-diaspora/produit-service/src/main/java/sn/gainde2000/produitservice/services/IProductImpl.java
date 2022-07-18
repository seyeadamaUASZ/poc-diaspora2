package sn.gainde2000.produitservice.services;

import org.springframework.stereotype.Service;
import sn.gainde2000.produitservice.exceptions.ResourceNotFoundException;
import sn.gainde2000.produitservice.feignClients.CategoryRestClient;
import sn.gainde2000.produitservice.models.Category;
import sn.gainde2000.produitservice.models.Product;
import sn.gainde2000.produitservice.repositories.ProductRepository;

import java.util.List;


@Service
public class IProductImpl implements IProduct {
    private ProductRepository productRepository;
    private CategoryRestClient categoryRestClient;

    public IProductImpl(ProductRepository productRepository, CategoryRestClient categoryRestClient) {
        this.productRepository = productRepository;
        this.categoryRestClient = categoryRestClient;
    }


    @Override
    public Product addProduct(Product product) {
        Category category=null;
        try{
            category = categoryRestClient.getCategory(product.getCategoryId());
        }catch(Exception e){
            new ResourceNotFoundException("category not found");
        }
        Product product1 = this.productRepository.save(product);
        product1.setCategory(category);
        return product1;
    }

    @Override
    public List<Product> listProduct() {
        List<Product> products= this.productRepository.findAll();
        for(Product p:products){
            Category category = categoryRestClient.getCategory(p.getCategoryId());
            p.setCategory(category);
        }
        return products;
    }

    @Override
    public Product getProduct(Long id) throws ResourceNotFoundException {

        Product product= this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found for this id :: " + id));
        Category category = categoryRestClient.getCategory(product.getCategoryId());
        product.setCategory(category);
        return product;
    }
}
