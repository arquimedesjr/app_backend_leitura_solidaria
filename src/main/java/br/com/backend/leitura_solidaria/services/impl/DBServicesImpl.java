package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.enuns.TypeUsers;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.DBServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class DBServicesImpl implements DBServices {

    private final UsersRepository usersRepository;
    private final AddressRepository addressRepository;
    private final OrganizationRepository organizationRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void instantiateTestDataBase() {

        ProfileEntity profile1 = ProfileEntity.builder().id(TypeUsers.ONG.getCod()).type(TypeUsers.ONG.getDescription()).build();
        ProfileEntity profile2 = ProfileEntity.builder().id(TypeUsers.PARTNER.getCod()).type(TypeUsers.PARTNER.getDescription()).build();
        ProfileEntity profile3 = ProfileEntity.builder().id(TypeUsers.USER.getCod()).type(TypeUsers.USER.getDescription()).build();
        ProfileEntity profile4 = ProfileEntity.builder().id(TypeUsers.CLIENT.getCod()).type(TypeUsers.CLIENT.getDescription()).build();
        ProfileEntity profile5 = ProfileEntity.builder().id(TypeUsers.ADMIN.getCod()).type(TypeUsers.ADMIN.getDescription()).build();

        profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3, profile4, profile5));

        OrganizationEntity org1 = OrganizationEntity.builder().id(null).name("Todos Pela Saude LTDA").mail("saude@mail.com")
                .numCnpj("47241198000169").phones(new HashSet<>(Arrays.asList("11948924982", "1120811446")))
                .profile(profile1).build();

        OrganizationEntity org2 = OrganizationEntity.builder().id(null).name("Uniformes LTDA").mail("uniformes@mail.com")
                .numCnpj("47241198000169").profile(profile2).phones(new HashSet<>(Arrays.asList("11948924982", "1120811446")))
                .build();

        AddressEntity address1 = AddressEntity.builder().street("Rua flores").number("300").complement("apto 303").district("Jardim").cep("03014000").city("Urberlandia").organization(org2).build();
        AddressEntity address3 = AddressEntity.builder().street("AV rio flores").number("1").complement("apto 111").district("Jardim").cep("03014232").city("Sao Paulo").organization(org2).build();
        AddressEntity address2 = AddressEntity.builder().street("Avenida Matos").number("105").complement("sala 800").district("Centro").cep("03014232").city("Sao Paulo").organization(org1).build();

        organizationRepository.saveAll(Arrays.asList(org1, org2));
        addressRepository.saveAll(Arrays.asList(address1, address2, address3));

        UsersEntity us1 = UsersEntity.builder().id(null).fullName("Arquimedes Junior").mail("main@junior.com").password(bCryptPasswordEncoder.encode("4321")).urlImg("https:/teste.imagem.com").profile(org1.getProfile()).organization(org1).build();
        UsersEntity us2 = UsersEntity.builder().id(null).fullName("João Batista").mail("main@josdaqao.com").password(bCryptPasswordEncoder.encode("4321")).urlImg("https:/teste.imagem.com").profile(org2.getProfile()).organization(org2).build();
        UsersEntity us4 = UsersEntity.builder().id(null).fullName("admin").mail("admin@admin.com").password(bCryptPasswordEncoder.encode("admin")).profile(profile5).urlImg(null).organization(null).build();
        usersRepository.saveAll(Arrays.asList(us1, us2, us4));
    }
}
