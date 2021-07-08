package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.PartnerResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.PartnerPaginationResponse;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.PartnerEntity;
import br.com.backend.leitura_solidaria.models.repositories.PartnerRepository;
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

    @Override
    public List<PartnerResponse> findAll(ModelMapper mapper) {
        List<PartnerResponse> usersResponses = new LinkedList<>();
        List<PartnerEntity> usersList = partnerRepository.findAll();

        for (PartnerEntity entity : usersList) {
            usersResponses.add(mapper.map(entity, PartnerResponse.class));
        }
        return usersResponses;
    }

    @Override
    public PartnerResponse find(Integer id, ModelMapper mapper) {
        Optional<PartnerEntity> obj = partnerRepository.findById(id);

        if (obj.isPresent()) {
            return mapper.map(obj.get(), PartnerResponse.class);
        }

        throw new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PartnerResponse.class.getName());
    }

    @Override
    public PartnerResponse insert(UsersRequest obj, ModelMapper mapper) {
        try {


            PartnerEntity objEntity = PartnerEntity.builder()

                    .build();

            return mapper.map(partnerRepository.save(objEntity), PartnerResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(UsersRequest obj, Integer id) {
        PartnerEntity newObj = find(id);

        partnerRepository.save(PartnerEntity.builder()
                .id(newObj.getId())

                .build());
    }

    @Override
    public void delete(Integer id) {
        PartnerEntity obj = find(id);
        partnerRepository.delete(obj);
    }

    @Override
    public PartnerPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        Page<PartnerEntity> all = partnerRepository.findAll(pageRequest);

        List<PartnerResponse> usersResponses = new LinkedList<>();

        for (PartnerEntity entity : all.getContent()) {
            usersResponses.add(mapper.map(entity, PartnerResponse.class));
        }

        return PartnerPaginationResponse.builder()
                .count(all.getNumberOfElements())
                .partner(usersResponses).build();
    }



    private PartnerEntity find(Integer id) {
        Optional<PartnerEntity> obj = partnerRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PartnerEntity.class.getName()));
    }


}

