package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.models.entity.*;
import br.com.backend.leitura_solidaria.models.enuns.TypeUsers;
import br.com.backend.leitura_solidaria.models.repositories.*;
import br.com.backend.leitura_solidaria.services.DBServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class DBServicesImpl implements DBServices {

    private final UsersRepository usersRepository;
    private final AddressRepository addressRepository;
    private final PartnerRepository partnerRepository;
    private final ProfileRepository profileRepository;
    private final OngsRepository ongsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void instantiateTestDataBase() {

        Set<String> phones = new HashSet<>(Arrays.asList("11934878573", "47367439993"));


        PartnerEntity partner1 = PartnerEntity.builder().id(null).mail("mai@mail.com").name("JI Confecções").numCnpj("39748267812632").phones(phones).build();
        PartnerEntity partner2 = PartnerEntity.builder().id(null).mail("jamai@mail.com").name("FLWT Automotivo").numCnpj("39748267812632").phones(phones).build();
        partnerRepository.saveAll(Arrays.asList(partner1,partner2));


        OngsEntity ongs1 = OngsEntity.builder().id(null).mail("saude@mail.com").name("Todos pela Saude").numCnpj("39748267812632").phones(phones).build();
        OngsEntity ongs2 = OngsEntity.builder().id(null).mail("paz@mail.com").name("Todos pela Paz").numCnpj("39748267812632").phones(phones).build();
        ongsRepository.saveAll(Arrays.asList(ongs1,ongs2));


        ProfileEntity profile1 = ProfileEntity.builder().id(TypeUsers.ONG.getCod()).type(TypeUsers.ONG.getDescription()).build();
        ProfileEntity profile3 = ProfileEntity.builder().id(TypeUsers.USER.getCod()).type(TypeUsers.USER.getDescription()).build();
        ProfileEntity profile2 = ProfileEntity.builder().id(TypeUsers.PARTNER.getCod()).type(TypeUsers.PARTNER.getDescription()).build();
        ProfileEntity profile4 = ProfileEntity.builder().id(TypeUsers.CLIENT.getCod()).type(TypeUsers.CLIENT.getDescription()).build();
        ProfileEntity profile5 = ProfileEntity.builder().id(TypeUsers.ADMIN.getCod()).type(TypeUsers.ADMIN.getDescription()).build();

        profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3, profile4, profile5));


        AddressEntity address1 = AddressEntity.builder().street("Rua flores").number("300").complement("apto 303").district("Jardim").cep("03014000").state("São Paulo").city("Urberlandia").ongs(ongs1).build();
        AddressEntity address4 = AddressEntity.builder().street("AV rio flores").number("1").complement("apto 111").district("Jardim").cep("03014232").state("São Paulo").city("Sao Paulo").partner(partner2).build();
        AddressEntity address3 = AddressEntity.builder().street("AV rio flores").number("1").complement("apto 111").district("Jardim").cep("03014232").state("São Paulo").city("Sao Paulo").partner(partner1).build();
        AddressEntity address2 = AddressEntity.builder().street("Avenida Matos").number("105").complement("sala 800").district("Centro").cep("03014232").state("São Paulo").city("Sao Paulo").partner(partner1).build();

        addressRepository.saveAll(Arrays.asList(address1, address2, address3,address4));

        UsersEntity us1 = UsersEntity.builder().id(null).fullName("Arquimedes Junior").mail("main@junior.com").password(bCryptPasswordEncoder.encode("4321")).urlImg("https:/teste.imagem.com")
                .profile(profile2).ongs(null).partner(partner1).build();
        UsersEntity us3 = UsersEntity.builder().id(null).fullName("Diego Souza").mail("main@diego.com").password(bCryptPasswordEncoder.encode("4321")).urlImg("https:/teste.imagem.com")
                .profile(profile2).ongs(null).partner(partner2).build();


        UsersEntity us2 = UsersEntity.builder().id(null).fullName("João Batista").mail("main@joao.com").password(bCryptPasswordEncoder.encode("4321"))
                .urlImg("https:/teste.imagem.com").profile(profile1).ongs(ongs1).partner(null).build();
        UsersEntity us5 = UsersEntity.builder().id(null).fullName("Pedro Souza").mail("main@pedro.com").password(bCryptPasswordEncoder.encode("4321"))
                .urlImg("https:/teste.imagem.com").profile(profile1).ongs(ongs2).partner(null).build();


        UsersEntity us4 = UsersEntity.builder().id(null).fullName("admin").mail("admin@admin.com").password(bCryptPasswordEncoder.encode("admin")).profile(profile5).build();
        usersRepository.saveAll(Arrays.asList(us1, us2, us4,us5,us3));
    }
}
