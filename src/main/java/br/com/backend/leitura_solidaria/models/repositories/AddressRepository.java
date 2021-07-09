package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    AddressEntity findByStreetAndNumberAndCep(String street, String number, String cep);
    List<AddressEntity> findByPartnerId(Integer id);
    List<AddressEntity> findByOngsId(Integer id);
}
