package sn.gainde2000.produitservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.gainde2000.produitservice.models.Category;

import java.util.List;

@FeignClient(name="category-service")
public interface CategoryRestClient {
    @GetMapping(path = "/category/{id}")
    Category getCategory(@PathVariable(name="id")Long id);

    @GetMapping(path="/category")
    List<Category> allCategories();

}
