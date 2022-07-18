package sn.gainde2000.produitservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product {
    @Id
    private Long id;
    private String nameProduct;
    private Long categoryId;
    @Transient
    private Category category;

    public Product(Long id, String nameProduct, Long categoryId, Category category) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Product(Long id, String nameProduct, Long categoryId) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public Product(Long id, String nameProduct, Category category) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(Long id, String nameProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
