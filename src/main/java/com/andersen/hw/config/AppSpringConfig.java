package com.andersen.hw.config;

import com.andersen.hw.service.TicketService;
import com.andersen.hw.service.TicketServiceImpl;
import com.andersen.hw.service.UserService;
import com.andersen.hw.service.UserServiceImpl;
import com.andersen.hw.storage.TicketStorageDao;
import com.andersen.hw.storage.UserStorageDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class AppSpringConfig {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = AppSpringConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
            }
            prop.load(input);
            URL = prop.getProperty("url");
            USER = prop.getProperty("user");
            PASSWORD = prop.getProperty("password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public UserService userService(UserStorageDao userStorageDao) {
        return new UserServiceImpl(userStorageDao);
    }

    @Bean
    public TicketService ticketService(TicketStorageDao ticketStorageDao) {
        return new TicketServiceImpl(ticketStorageDao);
    }

    @Bean
    public UserStorageDao userStorageDao(DataSource dataSource) {
        return new UserStorageDao(dataSource);
    }

    @Bean
    public TicketStorageDao ticketStorageDao(DataSource dataSource) {
        return new TicketStorageDao(dataSource);
    }
}
