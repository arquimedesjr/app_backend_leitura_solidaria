package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.services.ProfileService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<ProfileResponse> findAll(ModelMapper mapper) {
        List<ProfileResponse> responses = new LinkedList<>();
        List<ProfileEntity> entities = profileRepository.findAll();
        for (ProfileEntity pro : entities) {
            responses.add(mapper.map(pro, ProfileResponse.class));
        }
        return responses;
    }


}

