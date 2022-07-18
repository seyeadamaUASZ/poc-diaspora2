package sn.gainde2000.categoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gainde2000.categoryservice.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
