package dsa.practice.springBoot.component;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserComponent {
    private static final Logger logger = LoggerFactory.getLogger(UserComponent.class);

    @Autowired
    OrderComponent orderComponent;

    public UserComponent() {
        logger.info("UserComponent created");
    }
    public void processOrder() {
        orderComponent.processOrder();
    }

    @PostConstruct
    public void init() {
        logger.info("UserComponent initialized with OrderComponent: {}", orderComponent.hashCode());

    }
}

@Service
class OrderComponent {
    private static final Logger logger = LoggerFactory.getLogger(OrderComponent.class);

    void processOrder() {
        logger.info("Order processed");
    }

}
