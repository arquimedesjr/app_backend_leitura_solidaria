package br.com.backend.leitura_solidaria.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OngsCodeNameResponse {

    @JsonProperty("cod_ongs")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private String mail;
    @JsonIgnore
    private String numCnpj;
    @JsonIgnore
    private Set<String> phones;

}
