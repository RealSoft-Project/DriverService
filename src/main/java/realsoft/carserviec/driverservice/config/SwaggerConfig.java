package realsoft.carserviec.driverservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Set<String> responseProduceType = new HashSet<String>();
        responseProduceType.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class)
                .produces(responseProduceType)
                .consumes(responseProduceType)
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        Contact contact =
                new Contact("RealSoft LLC.", "http://www.realsoft.uz/", "info@reasoft.com");

        return new ApiInfo(
                "CAR SERVICE DOC",
                "Documentation for car service",
                "v1",
                "Terms of services",
                contact,
                "MIT licence",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>()
        );
    }
}