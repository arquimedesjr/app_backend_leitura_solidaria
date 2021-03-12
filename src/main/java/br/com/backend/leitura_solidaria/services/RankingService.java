package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Ranking;
import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.repositories.RankingRepository;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingService {

    @Autowired
    private RankingRepository repo;

    public List<Ranking> search(){
        return repo.findAll();
    }

    public Ranking save(Ranking ranking) {
        return repo.save(ranking);
    }

    public Ranking find(Integer id) {
        Optional<Ranking> obj =  repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Users.class.getName()));
    }

}
