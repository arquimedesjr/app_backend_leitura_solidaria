package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import br.com.backend.leitura_solidaria.domain.response.pagination.UsersPaginationResponse;
import br.com.backend.leitura_solidaria.exception.DataIntegrityException;
import br.com.backend.leitura_solidaria.exception.ObjectNotFoundException;
import br.com.backend.leitura_solidaria.models.entity.OngsEntity;
import br.com.backend.leitura_solidaria.models.entity.PartnerEntity;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.OngsRepository;
import br.com.backend.leitura_solidaria.models.repositories.PartnerRepository;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.services.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OngsRepository ongsRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public List<UsersResponse> findAll(ModelMapper mapper) {
        List<UsersResponse> usersResponses = new LinkedList<>();
        List<UsersEntity> usersList = usersRepository.findAll();

        for (UsersEntity entity : usersList) {
            usersResponses.add(mapper.map(entity, UsersResponse.class));
        }
        return usersResponses;
    }

    @Override
    public UsersResponse find(Integer id, ModelMapper mapper) {
        Optional<UsersEntity> obj = usersRepository.findById(id);

        if (obj.isPresent()) {
            return mapper.map(obj.get(), UsersResponse.class);
        }

        throw new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UsersResponse.class.getName());
    }

    @Override
    public UsersResponse insert(UsersRequest obj, ModelMapper mapper) {
        try {

            ProfileEntity profile = findProfile(obj.getProfile());

            UsersEntity objEntity = UsersEntity.builder()
                    .fullName(obj.getFullName())
                    .mail(obj.getMail())
                    .profile(profile)
                    .ongs(obj.getOngs() != null ? findOngs(obj.getOngs()) : null)
                    .partner(obj.getPartner() != null ? findPartner(obj.getPartner()) : null)
                    .password(bCryptPasswordEncoder.encode(obj.getPassword()))
                    .urlImg(obj.getUrlImg())
                    .build();

            return mapper.map(usersRepository.save(objEntity), UsersResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir o usuário");
        }
    }

    @Override
    public void update(UsersRequest obj, Integer id) {
        UsersEntity newObj = find(id);
        ProfileEntity profile = findProfile(obj.getProfile());

        usersRepository.save(UsersEntity.builder()
                .id(newObj.getId())
                .fullName(obj.getFullName())
                .mail(obj.getMail())
                .profile(profile)
                .ongs(obj.getOngs() != null ? findOngs(obj.getOngs()) : null)
                .partner(obj.getPartner() != null ? findPartner(obj.getPartner()) : null)
                .password(bCryptPasswordEncoder.encode(obj.getPassword()))
                .build());
    }

    @Override
    public void delete(Integer id) {
        UsersEntity obj = find(id);
        usersRepository.delete(obj);
    }

    @Override
    public UsersPaginationResponse findPage(Integer page, Integer linesPerPage, String orderBy, String direction, ModelMapper mapper) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));
        Page<UsersEntity> all = usersRepository.findAll(pageRequest);

        List<UsersResponse> usersResponses = new LinkedList<>();

        for (UsersEntity entity : all.getContent()) {
            usersResponses.add(mapper.map(entity, UsersResponse.class));
        }

        return UsersPaginationResponse.builder()
                .count(all.getNumberOfElements())
                .users(usersResponses).build();
    }

    @Override
    public UsersResponse findMail(String mail, ModelMapper mapper) {
        UsersEntity obj = usersRepository.findByMail(mail);

        if (obj != null) {
            return mapper.map(obj, UsersResponse.class);
        }

        throw new ObjectNotFoundException(
                "Objeto não encontrado!  Tipo: " + UsersResponse.class.getName());
    }


    private UsersEntity find(Integer id) {
        Optional<UsersEntity> obj = usersRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UsersEntity.class.getName()));
    }

    private ProfileEntity findProfile(Integer id) {
        Optional<ProfileEntity> obj = profileRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + ProfileEntity.class.getName()));
    }

    private OngsEntity findOngs(Integer id) {
        Optional<OngsEntity> obj = ongsRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OngsEntity.class.getName()));
    }

    private PartnerEntity findPartner(Integer id) {
        Optional<PartnerEntity> obj = partnerRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PartnerEntity.class.getName()));
    }
}

