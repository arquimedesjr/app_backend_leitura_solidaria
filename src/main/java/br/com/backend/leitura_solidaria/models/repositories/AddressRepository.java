package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.domain.Address;
import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    AddressEntity findByStreetAndNumber(final String street, final String number);

}
