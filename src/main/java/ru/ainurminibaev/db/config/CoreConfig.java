package ru.ainurminibaev.db.config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("ru.ainurminibaev.db.service")
public class CoreConfig {

    @Bean
	public Connection psql() throws Exception {
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/gamification_2015";
		String username = "postgres";
		String password = "postgres";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
