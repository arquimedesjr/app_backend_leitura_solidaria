package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {


}
