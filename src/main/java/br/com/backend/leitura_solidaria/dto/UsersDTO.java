package br.com.backend.leitura_solidaria.dto;

import br.com.backend.leitura_solidaria.domain.Users;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UsersDTO implements Serializable {


    private Integer idUsers;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String fullName;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String mail;
    private String password;
    private String urlImg;

    public UsersDTO() {
    }

    public UsersDTO(Users users) {
        idUsers = users.getIdUsers();
        fullName = users.getFullName();
        mail = users.getMail();
        password = users.getPassword();
        urlImg = users.geturlImg();
    }


    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
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
}
