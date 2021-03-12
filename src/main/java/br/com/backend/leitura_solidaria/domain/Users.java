package br.com.backend.leitura_solidaria.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsers;
    private String fullName;

    @Column(unique = true)
    private String mail;
    private String password;
    private String urlImg;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "users_ranking",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ranking_id")
    )
    private List<Ranking> rankings = new ArrayList<>();

    public Users() {
    }

    public Users(Integer idUsers, String fullName, String mail, String password, String urlImg) {
        this.idUsers = idUsers;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
        this.urlImg = urlImg;
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

    public String geturlImg() {
        return urlImg;
    }

    public void seturlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getIdUsers().equals(users.getIdUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsers());
    }
}
