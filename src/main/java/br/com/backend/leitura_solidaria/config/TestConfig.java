package br.com.backend.leitura_solidaria.config;

import br.com.backend.leitura_solidaria.services.impl.DBServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServicesImpl dbServicesImpl;

    @Bean
    public boolean instantiateDatabase(){
        dbServicesImpl.instantiateTestDataBase();
        return true;
    }
}
