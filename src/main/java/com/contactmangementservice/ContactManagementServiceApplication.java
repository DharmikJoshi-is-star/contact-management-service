package com.contactmangementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.contactmangementservice")
@EnableSwagger2
public class ContactManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementServiceApplication.class);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApplicationInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.contactmangementservice"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApplicationInfo() {
       return new ApiInfo("Contact Management Service", "API Documentation", "1.0", "urn:tos",
                new Contact("Dharmik Joshi", "", "dharmikj75@gmail.com"), "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }
}
