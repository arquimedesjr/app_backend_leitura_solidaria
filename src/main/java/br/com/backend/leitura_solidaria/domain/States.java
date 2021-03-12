package br.com.backend.leitura_solidaria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class States implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStates;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "states")
    private List<City> cityLis = new ArrayList<>();

    public States(){
    }

    public States(Integer idStates, String name) {
        this.idStates = idStates;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof States)) return false;
        States city = (States) o;
        return getIdStates().equals(city.getIdStates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdStates());
    }

    public Integer getIdStates() {
        return idStates;
    }

    public void setIdStates(Integer idStates) {
        this.idStates = idStates;
    }

    public List<City> getCityLis() {
        return cityLis;
    }

    public void setCityLis(List<City> cityLis) {
        this.cityLis = cityLis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
