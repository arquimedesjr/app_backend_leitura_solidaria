package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.models.entity.OngsEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngsRepository extends JpaRepository<OngsEntity, Integer> {


}
