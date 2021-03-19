package br.com.backend.leitura_solidaria.domain.enums;

public enum TypeUsers {

    ONG(1, "ONG"),
    USER(2, "Usuário"),
    PARTNER(3, "Parceiro");

    private int cod;
    private String description;

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
