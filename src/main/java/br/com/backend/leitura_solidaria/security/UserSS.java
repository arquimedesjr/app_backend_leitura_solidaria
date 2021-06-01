package br.com.backend.leitura_solidaria.security;

import br.com.backend.leitura_solidaria.models.enuns.TypeUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public class UserSS implements UserDetails {

    private Integer id;
    private String mail;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {}

    public UserSS(Integer id, String mail, String password, TypeUsers typeUsers) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.authorities = new LinkedHashSet<GrantedAuthority>(Collections.singleton(new SimpleGrantedAuthority(typeUsers.getDescription())));
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
