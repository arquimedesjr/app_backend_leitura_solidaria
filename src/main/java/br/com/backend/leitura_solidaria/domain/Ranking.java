package br.com.backend.leitura_solidaria.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Ranking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRanking;
    private Integer ranking;
    private String score;
    private Boolean dailyReading;
    private Boolean bookworm;

    @JsonManagedReference
    @ManyToMany(mappedBy="rankings")
    private List<Users> users = new ArrayList<>();

    public Ranking() {
    }

    public Ranking(Integer idRanking, Integer ranking, String score, Boolean dailyReading, Boolean bookworm) {
        this.idRanking = idRanking;
        this.ranking = ranking;
        this.score = score;
        this.dailyReading = dailyReading;
        this.bookworm = bookworm;
    }

    public Integer getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(Integer idRanking) {
        this.idRanking = idRanking;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Boolean getDailyReading() {
        return dailyReading;
    }

    public void setDailyReading(Boolean dailyReading) {
        this.dailyReading = dailyReading;
    }

    public Boolean getBookworm() {
        return bookworm;
    }

    public void setBookworm(Boolean bookworm) {
        this.bookworm = bookworm;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranking)) return false;
        Ranking ranking = (Ranking) o;
        return getIdRanking().equals(ranking.getIdRanking());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRanking());
    }

}
