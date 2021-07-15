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
public class OngsRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("num_cnpj")
    private String numCnpj;
    @JsonProperty("phones")
    private Set<String> phones;
    @JsonProperty("image")
    private String image;
    @JsonProperty("description")
    private String description;

    @JsonProperty("address")
    List<AddressRequest> address;

}
