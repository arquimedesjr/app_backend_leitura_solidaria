package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.OrganizationRequest;
import br.com.backend.leitura_solidaria.domain.response.AddressResponse;
import br.com.backend.leitura_solidaria.domain.response.OrganizationResponse;
import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final AddressRepository addressRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponse> findAll(ModelMapper mapper) {
        List<OrganizationResponse> organizationResponses = new LinkedList<>();
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll();

        if (organizationEntityList.isEmpty())
            throw new ObjectNotFoundException("Não possui nenhum registro de Organização");

        for (OrganizationEntity x : organizationEntityList) {
            organizationResponses.add(OrganizationResponse.builder()
                    .name(x.getName())
                    .profile(this.profileEntityforResponse(x.getProfile(), mapper))
                    .id(x.getId())
                    .mail(x.getMail())
                    .numCnpj(x.getNumCnpj())
                    .phones(x.getPhones())
                    .address(this.findAllAnddressforOrganization(x.getId(), mapper)).build());
        }

        return organizationResponses;
    }

    @Override
    public OrganizationResponse find(Integer id, ModelMapper mapper) {
        Optional<OrganizationEntity> obj = organizationRepository.findById(id);

        if (!obj.isPresent())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + OrganizationResponse.class.getName());

        return OrganizationResponse.builder()
                .id(obj.get().getId())
                .name(obj.get().getName())
                .numCnpj(obj.get().getNumCnpj())
                .profile(this.profileEntityforResponse(obj.get().getProfile(), mapper))
                .address(this.findAllAnddressforOrganization(obj.get().getId(), mapper))
                .phones(obj.get().getPhones())
                .mail(obj.get().getMail())
                .build();
    }

    @Override
    public OrganizationResponse insert(OrganizationRequest obj, ModelMapper mapper) {

        return null;

    }

    @Override
    public void update(OrganizationRequest obj, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Page<OrganizationEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return null;
    }


    private List<AddressResponse> findAllAnddressforOrganization(Integer id, ModelMapper mapper) {
        List<AddressResponse> addressResponse = new LinkedList<>();
        addressRepository.findByOrganizationId(id).forEach(addressEntity ->
                addressResponse.add(mapper.map(addressEntity, AddressResponse.class))
        );
        return addressResponse;
    }

    private ProfileResponse profileEntityforResponse(ProfileEntity entity, ModelMapper mapper) {
        return mapper.map(entity, ProfileResponse.class);
    }
}
