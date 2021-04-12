package br.com.backend.leitura_solidaria.domain.enums;

public enum Profiles {

    ADMIN(1, "ROLES_ADMIN"),
    CLIENT(2, "ROLES_CLIENT");


    private int cod;
    private String description;

    Profiles(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Profiles toenum(Integer cod) {

        if (cod == null)
            return null;

        for (Profiles x : Profiles.values()) {
            if (cod.equals(x.getCod()))
                return x;
        }
        throw new IllegalArgumentException("id inválido: " + cod);
    }
}
