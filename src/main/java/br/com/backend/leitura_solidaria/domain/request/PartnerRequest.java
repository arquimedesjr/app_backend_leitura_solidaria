package br.com.backend.leitura_solidaria.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("num_cnpj")
    private String numCnpj;
    @JsonProperty("profile")
    private Integer profile;
    @JsonProperty("phones")
    private Set<String> phones;
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
    @JsonProperty("zip_code")
    private String cep;

}
