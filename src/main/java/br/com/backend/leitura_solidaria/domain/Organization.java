package br.com.backend.leitura_solidaria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {

    private Integer id;
    private String name;
    private String mail;
    private String numCnpj;
    private Profile profile;
    private List<Address> address;
    private Set<String> phones;

}
