package br.com.postech.java.tech.challenge.cortistyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CortistyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CortistyleApplication.class, args);
    }
}
