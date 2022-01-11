package gr.codelearn.smdb.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableJpaRepositories(namedQueriesLocation = "classpath:jpa-named-queries.properties")
public class SmdbApplication {

    private static final Logger logger = LoggerFactory.getLogger(SmdbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmdbApplication.class, args);

        logger.info("Starting program execution");

    }
}
