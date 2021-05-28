package br.com.backend.leitura_solidaria.resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(OrganizationController.class)
@AutoConfigureMockMvc
public class OrganizationControllerTest {


    @Test
    @DisplayName("Deve retornar codigo 200")
    public void deveRetornarCode200(){

    }

}