package pl.edu.pja.prz.finances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-finances.properties")
public class FinancesConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(FinancesConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("Loaded module: FINANCES");
    }
}
