package restapi.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String ERRORRESPONSE = "ErrorResponse";
    private static final List<ResponseMessage> GLOBALRESPONSES = Arrays.asList(
            new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).responseModel(new ModelRef(ERRORRESPONSE))
                    .build(),
            new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
                    .message(HttpStatus.NOT_FOUND.getReasonPhrase()).responseModel(new ModelRef(ERRORRESPONSE)).build(),
            new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .responseModel(new ModelRef(ERRORRESPONSE)).build());
    private static final ApiInfo APIINFO = new ApiInfo("Example of a Rest API.",
            "Example of a REST API, using Swagger documentation.", "1.0", "Terms of Service",
            new Contact("Felipe Sulzbach", "https://www.linkedin.com/in/felipe-sulzbach-8a2537a9/",
                    "feliperpdr@yahoo.com.br"),
            "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).globalResponseMessage(RequestMethod.GET, GLOBALRESPONSES)
                .globalResponseMessage(RequestMethod.POST, GLOBALRESPONSES)
                .globalResponseMessage(RequestMethod.DELETE, GLOBALRESPONSES)
                .globalResponseMessage(RequestMethod.PATCH, GLOBALRESPONSES).select()
                .apis(RequestHandlerSelectors.basePackage("restapi")).build().useDefaultResponseMessages(false)
                .apiInfo(APIINFO).directModelSubstitute(Temporal.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class);
    }

    @Bean
    public UiConfiguration uiConfigProd() {
        // "get", "delete", "options", "head", "patch", "trace"
        final String[] methodsWithTryItOutButton = { "get" };
        return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithTryItOutButton).build();
    }
}
