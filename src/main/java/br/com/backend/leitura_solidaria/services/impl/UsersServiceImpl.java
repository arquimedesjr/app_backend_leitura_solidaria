package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.OrganizationUser;
import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public List<Users> findAll(ModelMapper mapper) {
        List<UsersEntity> usersList = usersRepository.findAll();
        return verifyOngPatner(mapper, usersList);
    }

    @Override
    public Users find(Integer id, ModelMapper mapper) {
        Optional<UsersEntity> obj = usersRepository.findById(id);
        Optional<Users> users = Optional.ofNullable(verifyOrganization(obj.get(), mapper));
        return users.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Users.class.getName()));
    }

    public UsersEntity find(Integer id) {
        Optional<UsersEntity> obj = usersRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Users.class.getName()));
    }

    @Override
    public UsersEntity insert(UsersEntity obj) {
        try {
            obj.setId(null);
            return usersRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(UsersEntity obj) {
        UsersEntity newObj = find(obj.getId());

        usersRepository.save(UsersEntity.builder()
                .id(newObj.getId())
                .fullName(obj.getFullName())
                .mail(obj.getMail())
                .organization(newObj.getOrganization())
                .profile(newObj.getOrganization().getProfile())
                .password(obj.getPassword())
                .build());
    }

    @Override
    public void delete(Integer id) {
        UsersEntity obj = find(id);
        usersRepository.delete(obj);
    }

    @Override
    public Page<UsersEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        return usersRepository.findAll(pageRequest);
    }


    public List<Users> verifyOngPatner(ModelMapper mapper, List<UsersEntity> usersList) {
        List<Users> users = new LinkedList<>();

        for (UsersEntity entity : usersList) {
            if (entity.getOrganization() != null) {
                users.add(verifyOrganization(entity, mapper));
            }
        }
        return users;
    }

    public Users verifyOrganization(UsersEntity entity, ModelMapper mapper) {
        if (entity.getOrganization().getProfile().getType().equals("ONG")) {
            return Users.builder()
                    .ong(mapper.map(entity.getOrganization(), OrganizationUser.class))
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), Profile.class))
                    .partner(null)
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();

        } else if (entity.getOrganization().getProfile().getType().equals("PARTNER")) {
            return Users.builder()
                    .ong(null)
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), Profile.class))
                    .partner(mapper.map(entity.getOrganization(), OrganizationUser.class))
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();
        } else {
            return Users.builder()
                    .ong(null)
                    .urlImg(entity.getUrlImg())
                    .password(entity.getPassword())
                    .fullName(entity.getFullName())
                    .profile(mapper.map(entity.getProfile(), Profile.class))
                    .partner(null)
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .build();
        }
    }

}
