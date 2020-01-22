package org.learning.by.example.movies.spring.web.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("movies-datasource")
public class DataSourceProperties {
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public PoolConfig getPool() {
        return pool;
    }

    public void setPool(PoolConfig pool) {
        this.pool = pool;
    }

    private String driver;
    private String connectionString;
    private String credentials;
    private Boolean readOnly;
    private PoolConfig pool;

    public static class PoolConfig {
        public int getMinConnections() {
            return minConnections;
        }

        public void setMinConnections(int minConnections) {
            this.minConnections = minConnections;
        }

        public int getMaxConnections() {
            return maxConnections;
        }

        public void setMaxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
        }

        private int minConnections;
        private int maxConnections;
    }

}
