package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.pagination.UsersPaginationResponse;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface UsersService {

    List<UsersResponse> findAll(ModelMapper mapper);

    UsersResponse find(Integer id, ModelMapper mapper);

    UsersResponse insert(UsersRequest obj, ModelMapper mapper);

    void update(UsersRequest obj, Integer id);

    void delete(Integer id);

    UsersPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper);

    UsersResponse findMail(String mail, ModelMapper mapper);
}
