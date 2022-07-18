package sn.gainde2000.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import sn.gainde2000.pi.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@PropertySources({ @PropertySource("classpath:application.properties") }) 
@ComponentScan("sn.gainde2000.pi")
@EnableFeignClients
public class BackendotpApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendotpApplication.class, args);
	}

}
