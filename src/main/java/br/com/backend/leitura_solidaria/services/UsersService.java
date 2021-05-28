package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService {

    List<Users> findAll(ModelMapper mapper);

    Users find(Integer id,  ModelMapper mapper);

    UsersEntity insert(UsersEntity obj);

    void update(UsersEntity obj);

    void delete(Integer id);

    Page<UsersEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
