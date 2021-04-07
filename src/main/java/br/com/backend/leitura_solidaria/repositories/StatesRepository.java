package br.com.backend.leitura_solidaria.repositories;

import br.com.backend.leitura_solidaria.domain.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatesRepository extends JpaRepository<States, Integer> {

}
