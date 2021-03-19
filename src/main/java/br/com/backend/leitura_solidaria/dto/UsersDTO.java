package br.com.backend.leitura_solidaria.dto;

import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.domain.Profile;
import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.domain.enums.TypeUsers;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersDTO implements Serializable {


    private Integer id;
    private String fullName;
    private String mail;
    private String password;
    private String urlImg;
    private Profile profile;
    private HashMap<String, Object> partner = new LinkedHashMap<>();
    private HashMap<String, Object> ong = new LinkedHashMap<>();

    public UsersDTO() {
    }

    public UsersDTO(Users users) {
        id = users.getId();
        fullName = users.getFullName();
        mail = users.getMail();
        password = users.getPassword();
        urlImg = users.getUrlImg();
        profile = users.getProfile();
        partner = validOrganizationPartner(users.getOrganization());
        ong = validOrganizationOng(users.getOrganization());
    }

    public HashMap<String, Object> validOrganizationPartner(Organization organization) {
        if (profile.getName().equals(TypeUsers.PARTNER.getDescription()) && organization.getProfile().getDescription().equals(profile.getName())) {
            partner.put("id", organization.getId());
            partner.put("name", organization.getFullName());
            return partner;
        }
        return null;
    }


    public HashMap<String, Object> validOrganizationOng(Organization organization) {

        if (profile.getName().equals(TypeUsers.ONG.getDescription()) && organization.getProfile().getDescription().equals(profile.getName())) {
            ong.put("id", organization.getId());
            ong.put("name", organization.getFullName());
            return ong;
        }
        return null;
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

    public Map<String, Object> getPartner() {
        return partner;
    }

    public void setPartner(HashMap<String, Object> partner) {
        this.partner = partner;
    }

    public Map<String, Object> getOng() {
        return ong;
    }

    public void setOng(HashMap<String, Object> ong) {
        this.ong = ong;
    }
}
