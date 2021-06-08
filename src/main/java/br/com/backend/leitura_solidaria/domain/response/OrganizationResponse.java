package br.com.backend.leitura_solidaria.domain.response;

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
public class OrganizationResponse {

    @JsonProperty("cod_organization")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("num_cnpj")
    private String numCnpj;
    @JsonProperty("profile")
    private ProfileResponse profile;
    @JsonProperty("address")
    private List<AddressResponse> address;
    @JsonProperty("phones")
    private Set<String> phones;

}
