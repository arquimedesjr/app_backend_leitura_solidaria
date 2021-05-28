package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizationService {

    List<OrganizationEntity> findAll();

    OrganizationEntity find(Integer id);

    OrganizationEntity insert(OrganizationEntity obj);

    void update(OrganizationEntity obj);

    void delete(Integer id);

    Page<OrganizationEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
