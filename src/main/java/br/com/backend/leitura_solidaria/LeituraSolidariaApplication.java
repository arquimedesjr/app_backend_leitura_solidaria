package br.com.backend.leitura_solidaria;

import br.com.backend.leitura_solidaria.domain.*;
import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import br.com.backend.leitura_solidaria.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class LeituraSolidariaApplication implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private StatesRepository statesRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public static void main(String[] args) {
        SpringApplication.run(LeituraSolidariaApplication.class, args);
    }

    @Override
    public void run(String... args) {


        States st1 = new States(null, "Minhas Gerais");
        States st2 = new States(null, "São Paulo");

        City ct1 = new City(null, "Urberlândia", st1);
        City ct2 = new City(null, "São Paulo", st2);
        City ct3 = new City(null, "Campinas", st2);

        st1.getCityLis().add(ct1);
        st2.getCityLis().addAll(Arrays.asList(ct2, ct3));

        statesRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(ct1, ct2, ct3));

        Profile profile = new Profile(TypeUsers.ONG.getCod(),TypeUsers.ONG.getDescription());
        Profile profile1 = new Profile(TypeUsers.PARTNER.getCod(),TypeUsers.PARTNER.getDescription());
        Profile profile2 = new Profile(TypeUsers.USER.getCod(),TypeUsers.USER.getDescription());


        profileRepository.saveAll(Arrays.asList(profile,profile1,profile2));

        Organization org1 = new Organization(null, "Todos Pela Saúde LTDA", "saude@mail.com", "47241198000169", profile);
        Organization org2 = new Organization(null, "Uniformes LTDA", "uniformes@mail.com", "47241192000169", profile1);

        org1.getPhone().addAll(Arrays.asList("11948924982", "1120811446"));
        org2.getPhone().addAll(Arrays.asList("11948924982", "1120811446"));

        Address address1 = new Address(null, "Rua flores", "300", "apto 303", "Jardim", "03014000", org1, ct1);
        Address address2 = new Address(null, "Avenida Matos", "105", "sala 800", "Centro", "03014232", org1, ct2);

        org1.getAddressList().addAll(Arrays.asList(address1, address2));
        org2.getAddressList().addAll(Collections.singletonList(address1));

        organizationRepository.saveAll(Arrays.asList(org1,org2));
        addressRepository.saveAll(Arrays.asList(address1, address2));



        Users us1 = new Users(null, "Arquimedes Junior", "main@junior.com", "1234", "https:/teste.imagem.com", org1,profile);
        Users us2 = new Users(null, "João Batista", "main@josdaqao.com", "4321", "https:/teste.imagem.com", org1, profile1);
        Users us3 = new Users(null, "João Batista", "main@joasqsao.com", "4321", "https:/teste.imagem.com", org2, profile1);

        usersRepository.saveAll(Arrays.asList(us1, us2, us3));
    }

}
