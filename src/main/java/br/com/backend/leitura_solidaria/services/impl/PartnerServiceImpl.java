package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.AddressRequest;
import br.com.backend.leitura_solidaria.domain.request.PartnerRequest;
import br.com.backend.leitura_solidaria.domain.response.AddressResponse;
import br.com.backend.leitura_solidaria.domain.response.PartnerCodeNameResponse;
import br.com.backend.leitura_solidaria.domain.response.PartnerResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.PartnerPaginationResponse;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import br.com.backend.leitura_solidaria.models.entity.PartnerEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.PartnerRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.PartnerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {


    private final PartnerRepository partnerRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<PartnerResponse> findAll(ModelMapper mapper) {
        List<PartnerEntity> list = partnerRepository.findAll();
        return converToPartnerResponse(list, mapper);
    }

    @Override
    public List<PartnerCodeNameResponse> findAllCodeName(ModelMapper mapper) {
        List<PartnerEntity> list = partnerRepository.findAll();
        List<PartnerCodeNameResponse> partnerCodeNameResponses = new LinkedList<>();
        for (PartnerEntity partnerEntity : list) {
            partnerCodeNameResponses.add(mapper.map(partnerEntity, PartnerCodeNameResponse.class));
        }
        return partnerCodeNameResponses;
    }

    @Override
    public PartnerResponse find(Integer id, ModelMapper mapper) {

        PartnerEntity partnerEntity = find(id);
        List<AddressResponse> addressResponses = converToAddressResponse(partnerEntity, mapper);
        PartnerResponse partnerResponse = mapper.map(partnerEntity, PartnerResponse.class);
        partnerResponse.setAddress(addressResponses);

        return partnerResponse;
    }

    @Override
    public PartnerResponse insert(PartnerRequest obj, ModelMapper mapper) {
        try {

            PartnerEntity objEntity = mapper.map(obj, PartnerEntity.class);
            partnerRepository.save(objEntity);

            for(AddressRequest request: obj.getAddress()){
                AddressEntity map = mapper.map(request, AddressEntity.class);
                map.setPartner(objEntity);
                addressRepository.save(map);
            }

            return mapper.map(objEntity, PartnerResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(PartnerRequest obj, Integer id, ModelMapper mapper) {
        PartnerEntity newObj = find(id);

        PartnerEntity objEntity = mapper.map(obj, PartnerEntity.class);
        objEntity.setId(newObj.getId());
        partnerRepository.save(objEntity);

        List<AddressEntity> byPartnerId = addressRepository.findByPartnerId(id);
        for(AddressEntity entity: byPartnerId){
           addressRepository.delete(entity);
        }

        for(AddressRequest request: obj.getAddress()){
            AddressEntity map = mapper.map(request, AddressEntity.class);
            map.setPartner(objEntity);
            addressRepository.save(map);
        }
    }

    @Override
    public void delete(Integer id) {
        PartnerEntity obj = find(id);

        UsersEntity byPartnerId1 = usersRepository.findByPartnerId(id);
        byPartnerId1.setPartner(null);
        usersRepository.save(byPartnerId1);

        List<AddressEntity> byPartnerId = addressRepository.findByPartnerId(id);
        for(AddressEntity entity: byPartnerId){
            addressRepository.delete(entity);
        }

        partnerRepository.delete(obj);
    }

    @Override
    public PartnerPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper) {

        if (page > 0)
            page -= 1;

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        Page<PartnerEntity> all = partnerRepository.findAll(pageRequest);

        List<PartnerResponse> partnerResponses = new LinkedList<>();

        for (PartnerEntity entity : all.getContent()) {
            partnerResponses.add(mapper.map(entity, PartnerResponse.class));
        }
        return PartnerPaginationResponse.builder()
                .count(all.getNumberOfElements())
                .totalElements(all.getTotalElements())
                .totalPages(all.getTotalPages())
                .partner(partnerResponses).build();
    }


    private PartnerEntity find(Integer id) {
        Optional<PartnerEntity> obj = partnerRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PartnerEntity.class.getName()));
    }

    private List<PartnerResponse> converToPartnerResponse(List<PartnerEntity> list, ModelMapper mapper) {
        List<PartnerResponse> partnerResponses = new LinkedList<>();
        List<AddressResponse> addressResponses;
        for (PartnerEntity entity : list) {
            addressResponses = new LinkedList<>();
            List<AddressEntity> byPartnerId = addressRepository.findByPartnerId(entity.getId());

            PartnerResponse map = mapper.map(entity, PartnerResponse.class);

            for (AddressEntity addressEntity : byPartnerId) {
                addressResponses.add(mapper.map(addressEntity, AddressResponse.class));
            }

            map.setAddress(addressResponses);
            partnerResponses.add(map);
        }
        return partnerResponses;
    }

    private List<AddressResponse> converToAddressResponse(PartnerEntity entity, ModelMapper mapper) {
        List<AddressResponse> addressResponses = new LinkedList<>();
        List<AddressEntity> byPartnerId = addressRepository.findByPartnerId(entity.getId());
        for (AddressEntity addressEntity : byPartnerId) {
            addressResponses.add(mapper.map(addressEntity, AddressResponse.class));
        }
        return addressResponses;
    }
}

