package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.OrganizationRequest;
import br.com.backend.leitura_solidaria.domain.response.AddressResponse;
import br.com.backend.leitura_solidaria.domain.response.OrganizationResponse;
import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OrganizationRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final AddressRepository addressRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<OrganizationResponse> findAll(ModelMapper mapper) {
        List<OrganizationResponse> organizationResponses = new LinkedList<>();
        List<OrganizationEntity> organizationEntityList = organizationRepository.findAll();

        if (organizationEntityList.isEmpty())
            throw new ObjectNotFoundException("Não possui nenhum registro de Organização");

        organizationEntityList.forEach(x -> {
            List<AddressResponse> addressResponse = new LinkedList<>();
            ProfileResponse profileResponse = mapper.map(x.getProfile(), ProfileResponse.class);
            List<AddressEntity> addressEntityList = addressRepository.findByOrganizationId(x.getId());

            addressEntityList.forEach(addressEntity ->
                    addressResponse.add(mapper.map(addressEntity, AddressResponse.class))
            );

            organizationResponses.add(OrganizationResponse.builder().name(x.getName()).profile(profileResponse).id(x.getId()).mail(x.getMail()).numCnpj(x.getNumCnpj()).phones(x.getPhones()).address(addressResponse).build());

        });

        return organizationResponses;
    }

    @Override
    public OrganizationResponse find(Integer id, ModelMapper mapper) {
        return null;
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
}
