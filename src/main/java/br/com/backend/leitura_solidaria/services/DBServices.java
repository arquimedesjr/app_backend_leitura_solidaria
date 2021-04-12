package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.*;
import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import br.com.backend.leitura_solidaria.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private StatesRepository statesRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDataBase(){
        States st1 = new States(null, "Minhas Gerais");
        States st2 = new States(null, "São Paulo");


        statesRepository.saveAll(Arrays.asList(st1, st2));

        Profile profile = new Profile(TypeUsers.ONG.getCod(),TypeUsers.ONG.getDescription());
        Profile profile1 = new Profile(TypeUsers.PARTNER.getCod(),TypeUsers.PARTNER.getDescription());
        Profile profile2 = new Profile(TypeUsers.USER.getCod(),TypeUsers.USER.getDescription());
        Profile profile3 = new Profile(TypeUsers.ADMIN.getCod(),TypeUsers.ADMIN.getDescription());

        profileRepository.saveAll(Arrays.asList(profile,profile1,profile2,profile3));

        Organization org1 = new Organization(null, "Todos Pela Saúde LTDA", "saude@mail.com", "47241198000169", profile);
        Organization org2 = new Organization(null, "Uniformes LTDA", "uniformes@mail.com", "47241192000169", profile1);

        org1.getPhone().addAll(Arrays.asList("11948924982", "1120811446"));
        org2.getPhone().addAll(Arrays.asList("11948924982", "1120811446"));

        Address address1 = new Address(null, "Rua flores", "300", "apto 303", "Jardim", "03014000", org1, "Urberlândia");
        Address address2 = new Address(null, "Avenida Matos", "105", "sala 800", "Centro", "03014232", org1, "São Paulo");

        org1.getAddress().addAll(Arrays.asList(address1, address2));
        org2.getAddress().addAll(Collections.singletonList(address1));

        organizationRepository.saveAll(Arrays.asList(org1,org2));
        addressRepository.saveAll(Arrays.asList(address1, address2));



        Users us1 = new Users(null, "Arquimedes Junior", "main@junior.com", pe.encode("4321"), "https:/teste.imagem.com", org1,profile);
        Users us2 = new Users(null, "João Batista", "main@josdaqao.com", pe.encode("4321"), "https:/teste.imagem.com", org1, profile1);
        Users us3 = new Users(null, "João Batista", "main@joasqsao.com", pe.encode("4321"), "https:/teste.imagem.com", org2, profile1);
        Users us4 = new Users(null, "admin", "admin@admin.com", pe.encode("admin"), null, null, profile3);

        usersRepository.saveAll(Arrays.asList(us1, us2, us3,us4));
    }
}
