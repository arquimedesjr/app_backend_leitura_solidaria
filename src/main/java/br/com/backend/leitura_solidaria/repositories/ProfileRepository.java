package br.com.backend.leitura_solidaria.repositories;

import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {


}
