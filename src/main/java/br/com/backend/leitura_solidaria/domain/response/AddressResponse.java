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
public class AddressResponse {

    @JsonProperty("cod_addres")
    private Integer id;
    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("complement")
    private String complement;
    @JsonProperty("district")
    private String district;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip_code")
    private String cep;

}
