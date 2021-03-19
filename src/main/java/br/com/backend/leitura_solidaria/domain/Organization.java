package br.com.backend.leitura_solidaria.domain;

import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String mail;
    private String cnpj;
    private Integer profile;

    @OneToMany(mappedBy = "organization")
    private List<Address> addressList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phone = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "organization")
    private List<Users> users = new ArrayList<>();

    public Organization() {
    }

    public Organization(Integer id, String fullName, String mail, String cnpj, Profile profile) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.cnpj = cnpj;
        this.profile = profile.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TypeUsers getProfile() {
        return TypeUsers.toenum(profile);
    }

    public void setProfile(TypeUsers profile) {
        this.profile = profile.getCod();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }
}
