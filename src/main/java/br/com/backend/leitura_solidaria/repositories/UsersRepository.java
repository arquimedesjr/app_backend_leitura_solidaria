package br.com.backend.leitura_solidaria.repositories;

import br.com.backend.leitura_solidaria.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
