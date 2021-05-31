package br.com.backend.leitura_solidaria.models.enuns;

public enum TypeUsers {

    ONG(1, "ONG"),
    USER(2, "USER"),
    PARTNER(3, "PARTNER"),
    ADMIN(4, "ROLES_ADMIN"),
    CLIENT(5, "ROLES_CLIENT");

    private final int cod;
    private final String description;

    TypeUsers(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TypeUsers toenum(Integer cod) {

        if (cod == null)
            return null;

        for (TypeUsers x : TypeUsers.values()) {
            if (cod.equals(x.getCod()))
                return x;
        }
        throw new IllegalArgumentException("id inválido: " + cod);
    }
}
