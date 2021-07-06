package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.request.OrganizationRequest;
import br.com.backend.leitura_solidaria.domain.response.OrganizationResponse;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizationService {

    List<OrganizationResponse> findAll(ModelMapper mapper);

    OrganizationResponse find(Integer id, ModelMapper mapper);

    OrganizationResponse insert(OrganizationRequest obj, ModelMapper mapper);

    void update(OrganizationRequest obj, Integer id);

    void delete(Integer id);

    Page<OrganizationEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
