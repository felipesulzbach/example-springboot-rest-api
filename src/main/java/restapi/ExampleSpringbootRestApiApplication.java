package restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootApplication
public class ExampleSpringbootRestApiApplication {

    private static Logger LOG = LoggerFactory.getLogger(ExampleSpringbootRestApiApplication.class.getSimpleName());

    public static void main(String[] args) {
        try {
            System.setProperty("server.servlet.context-path", "/api");
            SpringApplication.run(ExampleSpringbootRestApiApplication.class, args);
        } catch (Throwable e) {
            if (e.getClass().getName().contains("SilentExitException")) {
                LOG.debug("Spring is restarting the main thread - See spring-boot-devtools");
            } else {
                LOG.error("Application crashed!", e);
            }
        }
    }
}
