package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.OrganizationUser;
import br.com.backend.leitura_solidaria.domain.response.Profile;
import br.com.backend.leitura_solidaria.domain.response.Users;
import br.com.backend.leitura_solidaria.security.JWTUtil;
import br.com.backend.leitura_solidaria.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerTest {
    static String USER_API = "/v1/users";

    @Autowired
    MockMvc mvc;

    @MockBean
    UsersService service;

    @MockBean
    JWTUtil jwtUtil;

    @Autowired
    ModelMapper mapper;

    @Test
    @DisplayName("Deve retornar codigo 200 para o API que busca todos os usuarios")
    public void deveRetornarGetParaTodosCode200() throws Exception {


        BDDMockito.given(service.findAll(mapper)).willReturn(buildUsers());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(USER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve retornar codigo 200 para o API que busca um usuario")
    public void deveRetornarGetParaUmCode200() throws Exception {

        BDDMockito.given(service.find(1, mapper)).willReturn(buildUsers().get(0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(USER_API.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar codigo 201 após inserir um usuario")
    public void deveRetornarCode201Insert() throws Exception {

        UsersRequest arquimedes_junior = UsersRequest.builder().fullName("Arquimedes Junior").mail("mail@mail.com.br").organization(1).urlImg(null).password("2131").build();
        String json = new ObjectMapper().writeValueAsString(arquimedes_junior);
        BDDMockito.given(service.insert(arquimedes_junior, mapper)).willReturn(buildUsers().get(0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(USER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar codigo 204 após atualizar um usuario")
    public void deveRetornarCode204Update() throws Exception {

        OrganizationUser todos_pela_saude = OrganizationUser.builder().id(1).name("Todos Pela Saude").build();
        Profile ong = Profile.builder().id(1).type("ONG").build();
        Users arquimedes_junior = Users.builder().fullName("Arquimedes Junior").mail("mail@mail.com.br").ong(todos_pela_saude).partner(null).profile(ong).urlImg(null).password("2131").build();
        String json = new ObjectMapper().writeValueAsString(arquimedes_junior);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(USER_API.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar codigo 204 após deletar um usuario")
    public void deveRetornarCode204Deletar() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(USER_API.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNoContent());
    }

    public List<Users> buildUsers() {
        List<Users> usersList = new LinkedList<>();

        OrganizationUser todos_pela_saude = OrganizationUser.builder().id(1).name("Todos Pela Saude").build();
        Profile ong = Profile.builder().id(1).type("ONG").build();
        Profile partner = Profile.builder().id(1).type("PARTNER").build();
        usersList.add(Users.builder().fullName("Arquimedes Junior").id(1).mail("mail@mail.com.br").ong(todos_pela_saude).partner(null).profile(ong).urlImg(null).password("2131").build());
        usersList.add(Users.builder().fullName("Romulo Larceda").id(2).mail("mail@mail.com.br").ong(null).partner(todos_pela_saude).profile(partner).urlImg(null).password("2131").build());

        return usersList;
    }


}