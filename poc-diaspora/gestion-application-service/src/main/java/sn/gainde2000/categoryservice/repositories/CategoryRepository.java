package sn.gainde2000.categoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.gainde2000.categoryservice.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    //aggrégat sur les catégories

    @Query("select count(c) from Category  c")
    Integer countCategories();
}
