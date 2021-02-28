package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repo;

    public Users search(Integer id){
        Optional<Users> obj =  repo.findById(id);
        return obj.orElse(null);
    }
}
