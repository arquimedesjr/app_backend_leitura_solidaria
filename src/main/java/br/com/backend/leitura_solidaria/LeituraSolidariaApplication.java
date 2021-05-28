package br.com.backend.leitura_solidaria;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LeituraSolidariaApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

    public static void main(String[] args) {
        SpringApplication.run(LeituraSolidariaApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }

}
