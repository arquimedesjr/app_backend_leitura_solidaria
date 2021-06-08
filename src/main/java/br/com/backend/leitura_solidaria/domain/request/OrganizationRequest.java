package br.com.backend.leitura_solidaria.domain.request;

import br.com.backend.leitura_solidaria.domain.response.AddressResponse;
import br.com.backend.leitura_solidaria.domain.response.ProfileResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
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
public class OrganizationRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("num_cnpj")
    private String numCnpj;
    @JsonProperty("profile")
    private Integer profile;
    @JsonProperty("cod_address")
    private List<Integer> address;
    @JsonProperty("phones")
    private Set<String> phones;

}
