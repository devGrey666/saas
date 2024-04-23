package com.grey.saas.master.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "spring.datasource")
public class MasterDatabaseConfigProperties {

    private String url;

    /**
     * database username
     */
    private String username;

    /**
     * database password
     */
    private String password;


    /**
     * database driver
     */
    private String driverClassName;

    // Following are for setting the Hikari Connection Pool properties. Spring
    // Boot uses Hikari CP by default.

    /**
     * Maximum number of milliseconds that a client will wait for a connection
     * from the pool. If this time is exceeded without a connection becoming
     * available, a SQLException will be thrown from
     * javax.sql.DataSource.getConnection().
     */
    private long connectionTimeout;

    /**
     * The property controls the maximum size that the pool is allowed to reach,
     * including both idle and in-use connections. Basically this value will
     * determine the maximum number of actual connections to the database
     * backend.
     * <p>
     * When the pool reaches this size, and no idle connections are available,
     * calls to getConnection() will block for up to connectionTimeout
     * milliseconds before timing out.
     */
    private int maxPoolSize;

    /**
     * This property controls the maximum amount of time (in milliseconds) that
     * a connection is allowed to sit idle in the pool. Whether a connection is
     * retired as idle or not is subject to a maximum variation of +30 seconds,
     * and average variation of +15 seconds. A connection will never be retired
     * as idle before this timeout. A value of 0 means that idle connections are
     * never removed from the pool.
     */
    private long idleTimeout;

    /**
     * The property controls the minimum number of idle connections that
     * HikariCP tries to maintain in the pool, including both idle and in-use
     * connections. If the idle connections dip below this value, HikariCP will
     * make the best effort to restore them quickly and efficiently.
     */
    private int minIdle;

    /**
     * The name for the master database connection pool
     */
    private String poolName;


}
