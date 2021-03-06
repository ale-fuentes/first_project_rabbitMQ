package edu.ale.teste.mySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLConnectionApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MySQLConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO messagebroker (message) VALUES (?)";

        int result = jdbcTemplate.update(sql, "mensage de teste 2");

        if(result > 0){
            System.out.println("A new row has been create with sucess!");
        }
    }

    public void saveMessage(String message) throws Exception{
        String sql = "INSERT INTO messagebroker (message) VALUES (?)";

        int result = jdbcTemplate.update(sql, message);

        if(result > 0){
            System.out.println("A new row has been create with sucess!");
        }
    }
}
