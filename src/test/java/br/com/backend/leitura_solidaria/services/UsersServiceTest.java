package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.Users;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.impl.DBServicesImpl;
import br.com.backend.leitura_solidaria.services.impl.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class UsersServiceTest {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ModelMapper mapper;

    UsersService service;
    DBServices dbServices;


    @BeforeEach
    public void setUp() {
        service = new UsersServiceImpl(usersRepository, organizationRepository);
        dbServices = new DBServicesImpl(usersRepository, addressRepository, organizationRepository, profileRepository);
        dbServices.instantiateTestDataBase();
    }

    @Test
    @DisplayName("Deve retornar todos usuarios")
    public void deveRetornarTodosUsuario() throws Exception {

        List<Users> users = service.findAll(mapper);

        String json = new ObjectMapper().writeValueAsString(users);

        var jsonExpect = "[{\"id\":15,\"name\":\"Arquimedes Junior\",\"mail\":\"main@junior.com\",\"url_image\":\"https:/teste.imagem.com\",\"profiles\":{\"id\":1,\"type\":\"ONG\"},\"ong\":{\"id\":9,\"name\":\"Todos Pela Saude LTDA\"},\"partner\":null},{\"id\":16,\"name\":\"João Batista\",\"mail\":\"main@josdaqao.com\",\"url_image\":\"https:/teste.imagem.com\",\"profiles\":{\"id\":3,\"type\":\"PARTNER\"},\"ong\":null,\"partner\":{\"id\":10,\"name\":\"Uniformes LTDA\"}},{\"id\":17,\"name\":\"admin\",\"mail\":\"admin@admin.com\",\"url_image\":null,\"profiles\":{\"id\":4,\"type\":\"ROLES_ADMIN\"},\"ong\":null,\"partner\":null}]";

        assertThat(json).isEqualTo(jsonExpect);
    }

    @Test
    @DisplayName("Deve retornar um usuario")
    public void deveRetornarUmUsuario() throws Exception {
        List<Users> userss = service.findAll(mapper);
        System.out.println(userss);
        Users users = service.find(5, mapper);

        String json = new ObjectMapper().writeValueAsString(users);

        var jsonExpect = "{\"id\":5,\"name\":\"Arquimedes Junior\",\"mail\":\"main@junior.com\",\"url_image\":\"https:/teste.imagem.com\",\"profiles\":{\"id\":1,\"type\":\"ONG\"},\"ong\":{\"id\":3,\"name\":\"Todos Pela Saude LTDA\"},\"partner\":null}";

        assertThat(json).isEqualTo(jsonExpect);
    }

    @Test
    @DisplayName("Deve retornar usuario sem vinculo a Organização")
    public void deveRetornarUsuarioSemVinculo() throws Exception {
        List<Users> userss = service.findAll(mapper);
        System.out.println(userss);
        Users users = service.find(12, mapper);

        String json = new ObjectMapper().writeValueAsString(users);

        var jsonExpect = "{\"id\":12,\"name\":\"Arquimedes Junior\",\"mail\":\"main@junior.com\",\"url_image\":\"https:/teste.imagem.com\",\"profiles\":{\"id\":1,\"type\":\"ONG\"},\"ong\":{\"id\":7,\"name\":\"Todos Pela Saude LTDA\"},\"partner\":null}";

        assertThat(json).isEqualTo(jsonExpect);
    }

    @Test
    @DisplayName("Deve retornar uma mensagem Usuario não encontrado")
    public void deveRetornarMensagemNaoEncontrado() throws Exception {

        try {
            service.find(88, mapper);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Objeto não encontrado! Id: 88, Tipo: br.com.backend.leitura_solidaria.domain.response.Users");
        }
    }

    @Test
    @DisplayName("Deve retornar uma mensagem Orgnização não encontrado")
    public void deveRetornarMensagemNaoEncontradoOrg() throws Exception {
        UsersRequest laercio = UsersRequest.builder().fullName("laercio Junior").mail("laercio@mail.com.br").organization(99).urlImg(null).password("2131").build();
        try {
            service.insert(laercio, mapper);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Objeto não encontrado! Id: 99, Tipo: br.com.backend.leitura_solidaria.models.entity.OrganizationEntity");
        }
    }

    @Test
    @DisplayName("Deve inserir um usuario")
    public void deveInserirUmUsuario() throws Exception {


        UsersRequest laercio = UsersRequest.builder().fullName("laercio Junior").mail("laercio@mail.com.br").organization(1).urlImg(null).password("2131").build();


        Users users = service.insert(laercio, mapper);
        String json = new ObjectMapper().writeValueAsString(users);

        var jsonExpect = "{\"id\":4,\"name\":\"laercio Junior\",\"mail\":\"laercio@mail.com.br\",\"url_image\":null,\"profiles\":{\"id\":1,\"type\":\"ONG\"},\"ong\":null,\"partner\":null}";

        assertThat(json).isEqualTo(jsonExpect);
    }

    @Test
    @DisplayName("Deve retornar uma mensagem Não foi possível inserir o usuário")
    public void deveRetornarMensagemUsuarioExistente() throws Exception {
        UsersRequest laercio = UsersRequest.builder().fullName("laercio Junior").mail("admin@admin.com").organization(5).urlImg(null).password("2131").build();
        List<Users> userss = service.findAll(mapper);
        System.out.println(userss);

        try {
            service.insert(laercio, mapper);
        } catch (DataIntegrityViolationException e) {
            assertThat(e.getMessage()).isEqualTo("Não foi possível inserir o usuário");
        }
    }

    @Test
    @DisplayName("Deve retornar uma mensagem Não foi possível inserir o usuário")
    public void deveDeletarUmUsuario() throws Exception {
        service.delete(1);
    }


}