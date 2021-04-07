package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.services.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repo;

    public List<Profile> findAll() {
        return repo.findAll();
    }

    public Profile find(Integer id) {
        Optional<Profile> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Profile.class.getName()));
    }

    public Profile insert(Profile obj) {
        try {
            obj.setId(null);
            return repo.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    public void delete(Integer id) {
        Profile obj = find(id);
        repo.delete(obj);
    }

    public Page<Profile> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }



}
