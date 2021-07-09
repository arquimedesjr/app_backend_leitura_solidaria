package br.com.backend.leitura_solidaria.models.repositories;

import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByMail(String mail);

    UsersEntity findByPartnerId(Integer id);

    UsersEntity findByOngsId(Integer id);

}
