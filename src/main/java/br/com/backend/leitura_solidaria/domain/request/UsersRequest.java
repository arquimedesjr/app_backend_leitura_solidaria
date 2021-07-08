package br.com.backend.leitura_solidaria.domain.request;

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

    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("url_image")
    private String urlImg;
    @JsonProperty("password")
    private String password;
    @JsonProperty("cod_profile")
    private Integer profile;
    @JsonProperty("cod_ongs")
    private Integer ongs;
    @JsonProperty("cod_partner")
    private Integer partner;

}
