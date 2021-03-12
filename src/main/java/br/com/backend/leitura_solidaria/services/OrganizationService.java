package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repo;

    public List<Organization> search(){
        return repo.findAll();
    }

    public Organization save(Organization Organization) {
        return repo.save(Organization);
    }

    public Organization find(Integer id) {
        Optional<Organization> obj =  repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Organization.class.getName()));
    }

}
