package br.com.backend.leitura_solidaria.repositories;

import br.com.backend.leitura_solidaria.domain.City;
import br.com.backend.leitura_solidaria.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

}
