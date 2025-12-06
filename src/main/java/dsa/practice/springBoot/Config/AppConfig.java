package dsa.practice.springBoot.Config;

import dsa.practice.springBoot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    @Lazy
    @Qualifier("raj")
    public User userBean() {
        logger.info("User Raj bean created");
        return new User("Raj", "123");
    }

    @Bean
    @Lazy
    @Qualifier("ravi")
    public User getUserBean() {
        logger.info("User Ravi bean created");
        return new User("Ravi", "@123");
    }


}
