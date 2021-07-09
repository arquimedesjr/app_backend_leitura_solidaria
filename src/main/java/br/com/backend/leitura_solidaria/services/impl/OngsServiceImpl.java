package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.AddressRequest;
import br.com.backend.leitura_solidaria.domain.request.OngsRequest;
import br.com.backend.leitura_solidaria.domain.response.AddressResponse;
import br.com.backend.leitura_solidaria.domain.response.OngsCodeNameResponse;
import br.com.backend.leitura_solidaria.domain.response.OngsResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.OngsPaginationResponse;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.AddressEntity;
import br.com.backend.leitura_solidaria.models.entity.OngsEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.AddressRepository;
import br.com.backend.leitura_solidaria.models.repositories.OngsRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.OngsService;
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
public class OngsServiceImpl implements OngsService {


    private final OngsRepository ongsRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<OngsResponse> findAll(ModelMapper mapper) {
        List<OngsEntity> list = ongsRepository.findAll();
        return converToOngsResponse(list, mapper);
    }

    @Override
    public List<OngsCodeNameResponse> findAllCodeName(ModelMapper mapper) {
        List<OngsEntity> list = ongsRepository.findAll();
        List<OngsCodeNameResponse> ongsCodeNameResponses = new LinkedList<>();
        for (OngsEntity ongsEntity : list) {
            ongsCodeNameResponses.add(mapper.map(ongsEntity, OngsCodeNameResponse.class));
        }
        return ongsCodeNameResponses;
    }

    @Override
    public OngsResponse find(Integer id, ModelMapper mapper) {

        OngsEntity ongsEntity = find(id);
        List<AddressResponse> addressResponses = converToAddressResponse(ongsEntity, mapper);
        OngsResponse ongsResponse = mapper.map(ongsEntity, OngsResponse.class);
        ongsResponse.setAddress(addressResponses);

        return ongsResponse;
    }

    @Override
    public OngsResponse insert(OngsRequest obj, ModelMapper mapper) {
        try {

            OngsEntity objEntity = mapper.map(obj, OngsEntity.class);
            ongsRepository.save(objEntity);

            for(AddressRequest request: obj.getAddress()){
                AddressEntity map = mapper.map(request, AddressEntity.class);
                map.setOngs(objEntity);
                addressRepository.save(map);
            }

            return mapper.map(objEntity, OngsResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(OngsRequest obj, Integer id, ModelMapper mapper) {
        OngsEntity newObj = find(id);

        OngsEntity objEntity = mapper.map(obj, OngsEntity.class);
        objEntity.setId(newObj.getId());
        ongsRepository.save(objEntity);

        List<AddressEntity> byOngsId = addressRepository.findByOngsId(id);
        for(AddressEntity entity: byOngsId){
           addressRepository.delete(entity);
        }

        for(AddressRequest request: obj.getAddress()){
            AddressEntity map = mapper.map(request, AddressEntity.class);
            map.setOngs(objEntity);
            addressRepository.save(map);
        }
    }

    @Override
    public void delete(Integer id) {
        OngsEntity obj = find(id);

        UsersEntity byOngsId1 = usersRepository.findByOngsId(id);
        byOngsId1.setOngs(null);
        usersRepository.save(byOngsId1);

        List<AddressEntity> byOngsId = addressRepository.findByOngsId(id);
        for(AddressEntity entity: byOngsId){
            addressRepository.delete(entity);
        }

        ongsRepository.delete(obj);
    }

    @Override
    public OngsPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        Page<OngsEntity> all = ongsRepository.findAll(pageRequest);

        List<OngsResponse> ongsResponses = new LinkedList<>();

        for (OngsEntity entity : all.getContent()) {
            ongsResponses.add(mapper.map(entity, OngsResponse.class));
        }
        return OngsPaginationResponse.builder()
                .count(all.getNumberOfElements())
                .totalElements(all.getTotalElements())
                .totalPages(all.getTotalPages())
                .ongs(ongsResponses).build();
    }


    private OngsEntity find(Integer id) {
        Optional<OngsEntity> obj = ongsRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OngsEntity.class.getName()));
    }

    private List<OngsResponse> converToOngsResponse(List<OngsEntity> list, ModelMapper mapper) {
        List<OngsResponse> ongsResponses = new LinkedList<>();
        List<AddressResponse> addressResponses;
        for (OngsEntity entity : list) {
            addressResponses = new LinkedList<>();
            List<AddressEntity> byOngsId = addressRepository.findByOngsId(entity.getId());

            OngsResponse map = mapper.map(entity, OngsResponse.class);

            for (AddressEntity addressEntity : byOngsId) {
                addressResponses.add(mapper.map(addressEntity, AddressResponse.class));
            }

            map.setAddress(addressResponses);
            ongsResponses.add(map);
        }
        return ongsResponses;
    }

    private List<AddressResponse> converToAddressResponse(OngsEntity entity, ModelMapper mapper) {
        List<AddressResponse> addressResponses = new LinkedList<>();
        List<AddressEntity> byOngsId = addressRepository.findByOngsId(entity.getId());
        for (AddressEntity addressEntity : byOngsId) {
            addressResponses.add(mapper.map(addressEntity, AddressResponse.class));
        }
        return addressResponses;
    }
}

