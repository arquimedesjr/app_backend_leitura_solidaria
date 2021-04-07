package br.com.backend.leitura_solidaria.dto;

import br.com.backend.leitura_solidaria.domain.Address;
import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrganizationDTO implements Serializable {


    private Integer id;
    private String fullName;
    private String mail;
    private String cnpj;
    private Integer profile;
    private List<Address> address = new ArrayList<>();
    private Set<String> phone = new HashSet<>();

    public OrganizationDTO() {
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

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idUsers) {
        this.id = idUsers;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
