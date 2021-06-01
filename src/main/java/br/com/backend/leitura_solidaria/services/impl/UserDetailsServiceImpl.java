package br.com.backend.leitura_solidaria.services.impl;

import br.com.backend.leitura_solidaria.domain.response.Profile;
import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.enuns.TypeUsers;
import br.com.backend.leitura_solidaria.models.repositories.ProfileRepository;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.security.UserSS;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UsersEntity usersEntity = usersRepository.findByMail(mail);
        if (usersEntity == null)
            throw new UsernameNotFoundException(mail);


        return new UserSS(usersEntity.getId(), usersEntity.getMail(), usersEntity.getPassword(), TypeUsers.toenum(usersEntity.getId()));
    }
}
