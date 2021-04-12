package br.com.backend.leitura_solidaria.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {

    private String mail;
    private String password;

    public CredentialsDTO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
