package br.com.backend.leitura_solidaria.domain.response;

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
public class Users {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String fullName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("url_image")
    private String urlImg;
    @JsonIgnore
    private String password;
    @JsonProperty("profiles")
    private Profile profile;
    @JsonProperty("ong")
    private OrganizationUser ong;
    @JsonProperty("partner")
    private OrganizationUser partner;

}
