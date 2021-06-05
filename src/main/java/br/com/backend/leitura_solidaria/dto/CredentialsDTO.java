package br.com.backend.leitura_solidaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialsDTO implements Serializable {

    private String mail;
    private String password;

}
