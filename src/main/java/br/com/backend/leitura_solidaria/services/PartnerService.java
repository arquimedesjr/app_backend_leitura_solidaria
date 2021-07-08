package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.PartnerResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.PartnerPaginationResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface PartnerService {

    List<PartnerResponse> findAll(ModelMapper mapper);

    PartnerResponse find(Integer id, ModelMapper mapper);

    PartnerResponse insert(UsersRequest obj, ModelMapper mapper);

    void update(UsersRequest obj, Integer id);

    void delete(Integer id);

    PartnerPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper);

}
