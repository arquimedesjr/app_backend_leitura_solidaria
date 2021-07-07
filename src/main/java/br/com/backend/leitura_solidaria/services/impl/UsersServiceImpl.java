package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.OrganizationUserResponse;
import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UsersResponse> findAll(ModelMapper mapper) {
        List<UsersEntity> usersList = usersRepository.findAll();
        return verifyOngPatner(mapper, usersList);
    }

    @Override
    public UsersResponse find(Integer id, ModelMapper mapper) {
        Optional<UsersEntity> obj = usersRepository.findById(id);

        if (obj.isPresent()) {
            return verifyOrganization(obj.get(), mapper);
        }

        throw new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UsersResponse.class.getName());
    }

    public UsersEntity find(Integer id) {
        Optional<UsersEntity> obj = usersRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UsersEntity.class.getName()));
    }

    public OrganizationEntity findOrg(Integer id) {
        Optional<OrganizationEntity> obj = organizationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OrganizationEntity.class.getName()));
    }

    @Override
    public UsersResponse insert(UsersRequest obj, ModelMapper mapper) {
        try {
            OrganizationEntity org = findOrg(obj.getOrganization());
            UsersEntity objEntity = UsersEntity.builder()
                    .fullName(obj.getFullName())
                    .mail(obj.getMail())
                    .organization(org)
                    .profile(org.getProfile())
                    .password(bCryptPasswordEncoder.encode(obj.getPassword()))
                    .urlImg(obj.getUrlImg())
                    .build();

            return mapper.map(usersRepository.save(objEntity), UsersResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(UsersRequest obj, Integer id) {
        UsersEntity newObj = find(id);

        OrganizationEntity org = findOrg(obj.getOrganization());

        usersRepository.save(UsersEntity.builder()
                .id(newObj.getId())
                .fullName(obj.getFullName())
                .mail(obj.getMail())
                .organization(org)
                .profile(org.getProfile())
                .password(obj.getPassword())
                .build());
    }

    @Override
    public void delete(Integer id) {
        UsersEntity obj = find(id);
        usersRepository.delete(obj);
    }

    @Override
    public List<UsersResponse> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        Page<UsersEntity> all = usersRepository.findAll(pageRequest);
        return verifyOngPatner(mapper,all.getContent());
    }

    @Override
    public UsersResponse findMail(String mail, ModelMapper mapper) {
        UsersEntity obj = usersRepository.findByMail(mail);

        if (obj != null) {
            return verifyOrganization(obj, mapper);
        }

        throw new ObjectNotFoundException(
                "Objeto não encontrado!  Tipo: " + UsersResponse.class.getName());
    }


    public List<UsersResponse> verifyOngPatner(ModelMapper mapper, List<UsersEntity> usersList) {
        List<UsersResponse> users = new LinkedList<>();
        for (UsersEntity entity : usersList) {
            users.add(verifyOrganization(entity, mapper));
        }
        return users;
    }

    public UsersResponse verifyOrganization(UsersEntity entity, ModelMapper mapper) {

        if (entity.getOrganization() != null && entity.getOrganization().getProfile().getType().equals("ONG")) {
            return UsersResponse.builder()
                    .ong(mapper.map(entity.getOrganization(), OrganizationUserResponse.class))
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), ProfileResponse.class))
                    .partner(null)
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();

        } else if (entity.getOrganization() != null && entity.getOrganization().getProfile().getType().equals("PARTNER")) {
            return UsersResponse.builder()
                    .ong(null)
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), ProfileResponse.class))
                    .partner(mapper.map(entity.getOrganization(), OrganizationUserResponse.class))
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();
        } else {
            return UsersResponse.builder()
                    .ong(null)
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), ProfileResponse.class))
                    .partner(null)
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();
        }
    }
}

