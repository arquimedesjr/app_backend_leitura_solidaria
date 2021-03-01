package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repo;

    public List<Users> search(){
        return repo.findAll();
    }

    public Users save(Users users) {
        return repo.save(users);
    }

    public Users find(Integer id) {
        Optional<Users> obj =  repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Users.class.getName()));
    }

}
