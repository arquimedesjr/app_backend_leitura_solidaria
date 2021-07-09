package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.OngsRequest;
import br.com.backend.leitura_solidaria.domain.response.OngsCodeNameResponse;
import br.com.backend.leitura_solidaria.domain.response.OngsResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.OngsPaginationResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface OngsService {

    List<OngsResponse> findAll(ModelMapper mapper);

    List<OngsCodeNameResponse> findAllCodeName(ModelMapper mapper);

    OngsResponse find(Integer id, ModelMapper mapper);

    OngsResponse insert(OngsRequest obj, ModelMapper mapper);

    void update(OngsRequest obj, Integer id, ModelMapper mapper);

    void delete(Integer id);

    OngsPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper);

}
