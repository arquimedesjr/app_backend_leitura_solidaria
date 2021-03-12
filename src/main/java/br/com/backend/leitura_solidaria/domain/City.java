package br.com.backend.leitura_solidaria.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCity;
    private String name;

    @ManyToOne
    @JoinColumn(name = "states_id")
    private States states;

    public City(){
    }

    public City(Integer idCity, String name, States states) {
        this.idCity = idCity;
        this.name = name;
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getIdCity().equals(city.getIdCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCity());
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
