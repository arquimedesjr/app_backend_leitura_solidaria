package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.UsersPaginationResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface ProfileService {

    List<ProfileResponse> findAll(ModelMapper mapper);

}
