package br.com.backend.leitura_solidaria;

import br.com.backend.leitura_solidaria.domain.*;
import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import br.com.backend.leitura_solidaria.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class LeituraSolidariaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(LeituraSolidariaApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }

}
