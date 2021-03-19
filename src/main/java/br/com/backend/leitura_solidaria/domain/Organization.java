package br.com.backend.leitura_solidaria.domain;

import br.com.backend.leitura_solidaria.domain.enums.TypeOrganization;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganization;
    private String fullName;
    private String mail;
    private String cnpj;
    private Integer type;

    @OneToMany(mappedBy = "organization")
    private List<Address> addressList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phone = new HashSet<>();

    public Organization() {
    }

    public Organization(Integer idOrganization, String fullName, String mail, String cnpj, TypeOrganization type) {
        this.idOrganization = idOrganization;
        this.fullName = fullName;
        this.mail = mail;
        this.cnpj = cnpj;
        this.type = (type == null) ? null : type.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getIdOrganization().equals(that.getIdOrganization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdOrganization());
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
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

    public TypeOrganization getType() {
        return TypeOrganization.toenum(type);
    }

    public void setType(TypeOrganization type) {
        this.type = type.getCod();
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
