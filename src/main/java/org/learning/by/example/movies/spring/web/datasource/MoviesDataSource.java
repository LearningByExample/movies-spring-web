package org.learning.by.example.movies.spring.web.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MoviesDataSource extends DriverManagerDataSource {
    private static final Log log = LogFactory.getLog(MoviesDataSource.class);

    MoviesDataSource(final DataSourceProperties dataSourceProperties) {
        super();
        this.setDriverClassName(dataSourceProperties.getDriver());
        setUrl(dataSourceProperties.getConnectionString());
        final String credentials = dataSourceProperties.getCredentials();
        this.setUsername(getCredentialValue(credentials, "username"));
        this.setPassword(getCredentialValue(credentials, "password"));
    }

    private String getCredentialValue(final String credentials, final String value) {
        final Path path = Paths.get(credentials, value);
        try {
            return new String(Files.readAllBytes(path));
        } catch (Exception ex) {
            throw new RuntimeException("error getting data source credential value : " + path.toString(), ex);
        }
    }
}
