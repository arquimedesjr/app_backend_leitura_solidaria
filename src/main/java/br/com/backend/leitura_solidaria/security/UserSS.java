//package br.com.backend.leitura_solidaria.security;
//
//import br.com.backend.leitura_solidaria.models.entity.ProfileEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class UserSS implements UserDetails {
//
//    private Integer id;
//    private String mail;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserSS() {
//    }
//
//    public UserSS(Integer id, String mail, String password, Set<ProfileEntity> profiles) {
//        this.id = id;
//        this.mail = mail;
//        this.password = password;
//        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getType())).collect(Collectors.toList());
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return mail;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
