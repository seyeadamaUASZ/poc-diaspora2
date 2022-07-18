package sn.gainde2000.produitservice.services;

import sn.gainde2000.produitservice.exceptions.ResourceNotFoundException;
import sn.gainde2000.produitservice.models.Product;

import java.util.List;

public interface IProduct {
    Product addProduct(Product product);
    List<Product> listProduct();
    Product getProduct(Long id) throws ResourceNotFoundException;
}
