package kr.co.dm7.blackpink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Dm7Application {

    public static void main(String[] args) {
        SpringApplication.run(Dm7Application.class, args);
    }
}