package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService {

    List<UsersResponse> findAll(ModelMapper mapper);

    UsersResponse find(Integer id, ModelMapper mapper);

    UsersResponse insert(UsersRequest obj, ModelMapper mapper);

    void update(UsersRequest obj, Integer id);

    void delete(Integer id);

    Page<UsersEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

    UsersResponse findMail(String mail, ModelMapper mapper);
}
