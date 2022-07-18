package sn.gainde2000.categoryservice.service;

import sn.gainde2000.categoryservice.entities.Category;
import sn.gainde2000.categoryservice.exceptions.CategoryNotFoundException;

import java.util.List;

public interface ICategory {
     Category addCategory(Category category);
     List<Category> listCategory();
     Category getCategory(Long id) throws CategoryNotFoundException;
}
