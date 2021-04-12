package br.com.backend.leitura_solidaria.services;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Users users = usersRepository.findByMail(mail);
        if(users==null)
            throw new UsernameNotFoundException(mail);

        return new UserSS(users.getId(),users.getMail(),users.getPassword(),users.getProfiles());
    }
}
