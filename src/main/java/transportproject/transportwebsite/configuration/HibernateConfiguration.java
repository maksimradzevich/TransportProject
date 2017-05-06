package transportproject.transportwebsite.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"transportproject.transportwebsite.dao"})
@ComponentScan({"transportproject.transportwebsite.configuration"})
public class HibernateConfiguration {

    private static final String DATASOURCE_PROPERTIES_PATH = "datasource.properties";
    private static final String HIBERNATE_PROPERTIES_PATH = "hibernate.properties";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setPackagesToScan("transportproject.transportwebsite.model");
        final Properties properties = getProperties(HIBERNATE_PROPERTIES_PATH);
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        // TODO узнать стоит ли заменить это все дело на что-то другое, чтобы на серваке не валилось
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("transportproject.transportwebsite.model");
        final Properties properties = getProperties(HIBERNATE_PROPERTIES_PATH);
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }


    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        final Properties properties = getProperties(DATASOURCE_PROPERTIES_PATH);
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        dataSource.setDriverClassName((String) properties.getProperty("jdbc.driverClassName"));
        dataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
        dataSource.setPassword((String) properties.getProperty("JDBC_DATABASE_PASSWORD"));
        return dataSource;
    }

    private Properties getProperties(String source) {
        Properties properties = new Properties();
        try (final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(source)){
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory s) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(s);
//        return txManager;
//    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }
}
