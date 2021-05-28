package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.Address;
import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;

    @Override
    public List<OrganizationEntity> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public OrganizationEntity find(Integer id) {
        Optional<OrganizationEntity> obj = organizationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Organization.class.getName()));
    }

    @Override
    public OrganizationEntity insert(OrganizationEntity obj) {
        try {
            obj.setId(null);
            return organizationRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir a organização");
        }
    }

    @Override
    public void update(OrganizationEntity obj) {
        OrganizationEntity newObj = find(obj.getId());
        hasAddress(obj);
        newObj = OrganizationEntity.builder().id(newObj.getId()).name(newObj.getName()).mail(newObj.getMail()).numCnpj(newObj.getNumCnpj()).profile(obj.getProfile()).build();
        organizationRepository.save(newObj);
    }

    @Override
    public void delete(Integer id) {
        OrganizationEntity obj = find(id);
        organizationRepository.delete(obj);
    }

    @Override
    public Page<OrganizationEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return organizationRepository.findAll(pageRequest);
    }


    public void hasAddress(OrganizationEntity obj) {
        if (!obj.getAddressEntities().isEmpty()) {

            obj.getAddressEntities().forEach(address -> {
                AddressEntity byStreetAndNumber = addressRepository.findByStreetAndNumber(address.getStreet(), address.getNumber());
                if (byStreetAndNumber != null)
                    addressRepository.save(byStreetAndNumber);
            });

        }
    }
}
