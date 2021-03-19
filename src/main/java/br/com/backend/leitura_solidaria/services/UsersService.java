package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
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
public class UsersService {

    @Autowired
    private UsersRepository repo;

    public List<Users> findAll() {
        return repo.findAll();
    }

    public Users find(Integer id) {
        Optional<Users> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Users.class.getName()));
    }

    public Users insert(Users obj) {
        try {
            obj.setId(null);
            return repo.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    public Users update(Users obj) {
        Users newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        Users obj = find(id);
        repo.delete(obj);
    }

    public Page<Users> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public void updateData(Users newObj, Users obj) {
        newObj.setFullName(obj.getFullName());
        newObj.setMail(obj.getMail());
        newObj.setPassword(obj.getPassword());
        newObj.setUrlImg(obj.getUrlImg());
        newObj.setProfile(obj.getProfile());
        newObj.setOrganization(obj.getOrganization());
    }

}
