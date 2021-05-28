package br.com.backend.leitura_solidaria.config;

import br.com.backend.leitura_solidaria.services.impl.DBServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBServicesImpl dbServicesImpl;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String stratefy;

    @Bean
    public boolean instantiateDatabase() {

        if (!"create".equals(stratefy))
            dbServicesImpl.instantiateTestDataBase();
        return true;
    }
}
