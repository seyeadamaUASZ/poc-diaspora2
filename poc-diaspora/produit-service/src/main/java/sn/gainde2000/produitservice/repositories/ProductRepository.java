package sn.gainde2000.produitservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gainde2000.produitservice.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
