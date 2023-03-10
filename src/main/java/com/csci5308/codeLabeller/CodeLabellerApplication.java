package com.csci5308.codeLabeller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class CodeLabellerApplication {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dev";
			String username = "root";
			String password = "1234";
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			String sql1 = "create table if not exists users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);";
			String sql2 = "create table if not exists authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));";
			String sql3 = "create unique index ix_auth_username on authorities (username,authority);";

			statement.execute(sql1);
			statement.execute(sql2);
			statement.execute(sql3);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			
		}

		SpringApplication.run(CodeLabellerApplication.class, args);
	}

}
