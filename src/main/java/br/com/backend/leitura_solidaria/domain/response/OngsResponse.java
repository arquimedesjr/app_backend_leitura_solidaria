package br.com.backend.leitura_solidaria.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OngsResponse {

    @JsonProperty("cod_ongs")
    private Integer id;
    @JsonProperty("name")
    private String name;

}
