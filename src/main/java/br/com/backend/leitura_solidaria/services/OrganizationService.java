package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Address;
import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.dto.OrganizationDTO;
import br.com.backend.leitura_solidaria.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.services.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repo;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public List<Organization> findAll() {
        return repo.findAll();
    }

    public Organization find(Integer id) {
        Optional<Organization> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Organization.class.getName()));
    }

    public Organization insert(Organization obj) {
        try {
            obj.setId(null);
            return repo.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir a organização");
        }
    }

    public Organization update(Organization obj) {
        Organization newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        Organization obj = find(id);
        repo.delete(obj);
    }

    public Page<Organization> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public void updateData(Organization newObj, Organization obj) {
        newObj.setFullName(obj.getFullName());
        newObj.setMail(obj.getMail());
        newObj.setCnpj(obj.getCnpj());
        newObj.setProfile(obj.getProfile());
        newObj.getAddress().addAll(obj.getAddress());
        newObj.setPhone(obj.getPhone());
    }

    public Organization fromDTO(OrganizationDTO obj) {
        Profile profile = null;

        Optional<Profile> repoprofile = profileRepository.findById(obj.getProfile());
        if (repoprofile.isPresent())
            profile = new Profile(repoprofile.get().getId(), repoprofile.get().getName());

        Organization organization = new Organization(null, obj.getFullName(), obj.getMail(), obj.getCnpj(), profile);
        List<Address> address = obj.getAddress();


        address.forEach(address1 -> {
            address1.setId(null);
            address1.setOrganization(organization);
        });


        organization.getPhone().addAll(obj.getPhone());
        organization.getAddress().addAll(obj.getAddress());

        this.insert(organization);
        addressRepository.saveAll(address);

        return organization;
    }

}
