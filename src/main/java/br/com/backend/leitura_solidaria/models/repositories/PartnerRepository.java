package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.models.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {


}
