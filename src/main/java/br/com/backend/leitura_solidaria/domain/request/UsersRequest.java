package br.com.backend.leitura_solidaria.domain.request;

import br.com.backend.leitura_solidaria.domain.response.OrganizationUser;
import br.com.backend.leitura_solidaria.domain.response.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("url_image")
    private String urlImg;
    @JsonProperty("password")
    private String password;
    @JsonProperty("cod_organization")
    private Integer organization;

}
