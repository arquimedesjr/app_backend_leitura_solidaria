package br.com.backend.leitura_solidaria.domain.enums;

public enum TypeOrganization {

    ONG(1, "ONG"),
    PESSOAJURIDICA(2, "Pessoa Jurídica"),
    PESSOAFISICA(3, "Pessoa Física");

    private int cod;
    private String description;

    TypeOrganization(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TypeOrganization toenum(Integer cod) {

        if (cod == null)
            return null;

        for (TypeOrganization x : TypeOrganization.values()) {
            if (cod.equals(x.getCod()))
                return x;
        }
        throw new IllegalArgumentException("id inválido: " + cod);
    }
}
