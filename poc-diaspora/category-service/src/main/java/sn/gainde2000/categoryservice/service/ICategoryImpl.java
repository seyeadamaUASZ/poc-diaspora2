package sn.gainde2000.categoryservice.service;

import org.springframework.stereotype.Service;
import sn.gainde2000.categoryservice.entities.Category;
import sn.gainde2000.categoryservice.exceptions.CategoryNotFoundException;
import sn.gainde2000.categoryservice.repositories.CategoryRepository;

import java.util.List;

@Service
public class ICategoryImpl implements ICategory {
    private CategoryRepository categoryRepository;

    public ICategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) throws CategoryNotFoundException {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found for this id :: " + id));
        return category;
    }
}
