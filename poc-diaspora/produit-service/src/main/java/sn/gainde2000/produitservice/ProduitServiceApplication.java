package sn.gainde2000.produitservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sn.gainde2000.produitservice.models.Product;
import sn.gainde2000.produitservice.services.IProduct;

@SpringBootApplication
@EnableFeignClients
public class ProduitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IProduct iProduct){
        return args -> {
           iProduct.addProduct(new Product(1L,"Ordinateur Hp",1L));
           iProduct.addProduct(new Product(2L,"banane",2L));
        };
    }

}
