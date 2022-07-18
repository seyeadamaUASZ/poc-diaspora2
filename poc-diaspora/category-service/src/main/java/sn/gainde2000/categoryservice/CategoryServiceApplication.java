package sn.gainde2000.categoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.gainde2000.categoryservice.entities.Category;
import sn.gainde2000.categoryservice.service.ICategory;

@SpringBootApplication
public class CategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ICategory iCategory){
        return args -> {
           iCategory.addCategory(new Category(1L,"electronic"));
           iCategory.addCategory(new Category(2L,"Aliment"));
        };
    }

}
