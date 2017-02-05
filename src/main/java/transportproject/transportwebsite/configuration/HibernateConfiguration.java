package transportproject.transportwebsite.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"transportproject.transportwebsite.configuration"})
@PropertySource(value = {"classpath:datasource.properties"})
public class HibernateConfiguration {


    @SuppressWarnings("FieldMayBeStatic")
    @Value("${jdbc.url}")
    private final String jdbcUrl = null;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        System.out.println(jdbcUrl);
        //TODO configure datasource
        //Or via hibernate properties
        return dataSource;
    }

}
