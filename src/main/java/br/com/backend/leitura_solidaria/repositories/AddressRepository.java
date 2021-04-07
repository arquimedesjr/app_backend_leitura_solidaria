package br.com.backend.leitura_solidaria.repositories;

import br.com.backend.leitura_solidaria.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
