package br.com.backend.leitura_solidaria.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsers;
    private String fullName;
    private String mail;
    private String password;
    private String img;

    public Users() {
    }

    public Users(Integer idUsers, String fullName, String mail, String password, String img) {
        this.idUsers = idUsers;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUsers=" + idUsers +
                ", fullName='" + fullName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(idUsers, users.idUsers) &&
                Objects.equals(fullName, users.fullName) &&
                Objects.equals(mail, users.mail) &&
                Objects.equals(password, users.password) &&
                Objects.equals(img, users.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsers, fullName, mail, password, img);
    }
}
