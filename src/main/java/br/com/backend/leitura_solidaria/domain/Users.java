package br.com.backend.leitura_solidaria.domain;

import br.com.backend.leitura_solidaria.domain.enums.Profiles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;



@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;

    @Column(unique = true)
    private String mail;
    private String urlImg;
    private String password;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    public Users() {
        addProfiles(Profiles.CLIENT);
    }
    public Users(Integer id, String fullName, String mail, String password, String urlImg, Organization organization, Profile profile) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
        this.urlImg = urlImg;
        this.organization = organization;
        this.profile = profile;
        addProfiles(Profiles.CLIENT);
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profiles> getProfiles(){
        return profiles.stream().map(x -> Profiles.toenum(x)).collect(Collectors.toSet());
    }
    public void addProfiles(Profiles profiles){
        this.profiles.add(profiles.getCod());
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
