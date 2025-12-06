package dsa.practice.springBoot.database;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @ConditionalOnProperty annotation controls bean creation based on application properties.
 * 
 * Parameters:
 * - name: The property key to check (e.g., "sql.enabled")
 * - havingValue: The expected value for the property (e.g., "true")
 * - matchIfMissing: If false, bean won't be created when property is absent; if true, bean is created when property is missing
 * 
 * Example: @ConditionalOnProperty(name = "sql.enabled", havingValue = "true", matchIfMissing = false)
 * This creates the bean ONLY when sql.enabled=true exists in application.properties.
 * If property is missing or has a different value, the bean won't be created.
 *
 * @ADVANTAGES
 * 1. Feature toggle support
 *    - Turn features ON/OFF using config only
 *
 * 2. Prevent unnecessary bean creation
 *    - Removes unused DB drivers, unused API handlers, unused services
 *
 * 3. Saves memory
 *    - Only necessary beans occupy heap
 *
 * 4. Faster application startup
 *    - Fewer beans created at boot time
 *
 * 5. Enables environment-specific behaviors
 *    - Local = MySQL
 *    - Production = NoSQL
 *    - No code change required
 *
 * @DISADVANTAGES
 * 1. Misconfiguration risk
 *    - Wrong key or wrong value → bean silently not created
 *
 * 2. Code complexity
 *    - Overuse leads to confusion about which beans load in which environment
 *
 * 3. Harder debugging
 *    - Missing beans due to config mismatch
 *
 * 4. Multiple beans sharing the same condition
 *    - If multiple classes use same prefix/value → potential confusion
 *
 * 9. SUMMARY
 * @ConditionalOnProperty is a powerful Spring Boot annotation that:
 * - Enables runtime bean activation/deactivation
 * - Helps with feature flags
 * - Avoids unnecessary beans in context
 * - Saves memory & startup time
 * - Must be combined with required=false when injecting conditional beans
 */
@Component
public class DataBaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConnection.class);
    @Autowired(required = false)
    MysqlConnection mysqlConnection;

    @Autowired(required = false)
    NoSqlConnection noSqlConnection;

    @PostConstruct
    public void init() {
        logger.info("DB Connection Bean created with dependencies below");
        if(mysqlConnection != null) {
            logger.info("MysqlConnection initialized");
        }
        if(noSqlConnection != null) {
            logger.info("NoSqlConnection initialized");
        }
    }
}

@Component
@ConditionalOnProperty(name = "sql.enabled", havingValue = "true", matchIfMissing = false)
class MysqlConnection{
    private static final Logger logger = LoggerFactory.getLogger(MysqlConnection.class);
    @PostConstruct
    public void init() {
        logger.info("MysqlConnection initialized");
    }
}

@Component
@ConditionalOnProperty(prefix = "nosql", name = "enabled", havingValue = "true")
class NoSqlConnection{
    private static final Logger logger = LoggerFactory.getLogger(NoSqlConnection.class);
    @PostConstruct
    public void init() {
        logger.info("NoSqlConnection initialized");
    }
}
