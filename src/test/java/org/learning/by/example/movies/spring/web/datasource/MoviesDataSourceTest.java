package org.learning.by.example.movies.spring.web.datasource;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoviesDataSourceTest {

    @Test
    void weCouldCreateADataSourceWithValidProperties() throws Exception {
        final DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setConnectionString("jdbc:postgresql://127.0.0.1:5432/movies");
        dataSourceProperties.setCredentials("src/test/resources/creds");
        dataSourceProperties.setDriver("org.postgresql.Driver");
        dataSourceProperties.setReadOnly(false);
        final DataSourceProperties.PoolConfig pool = new DataSourceProperties.PoolConfig();
        pool.setMinConnections(1);
        pool.setMaxConnections(3);
        dataSourceProperties.setPool(pool);

        final MoviesDataSource dataSource = new MoviesDataSource(dataSourceProperties);
        assertThat(dataSource).isNotNull();
    }

    @Test
    void weCouldNotCreateADataSourceWithInValidProperties() throws Exception {
        final DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setConnectionString("jdbc:postgresql://127.0.0.1:5432/movies");
        dataSourceProperties.setCredentials("wrong/path");
        dataSourceProperties.setDriver("org.postgresql.Driver");

        final Exception thrown = assertThrows(Exception.class, () -> new MoviesDataSource(dataSourceProperties), "Expected exception, but got none");
        assertThat(thrown).hasMessageContaining("error getting data source credential value");
    }
}