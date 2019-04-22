package br.com.SGP.utils;

public enum AmbienteVendasCliente {
    RUA("Rua"),
    SHOPPING("Shopping"),
    VIRTUAL("Virtual"),
    OUTROS("Outros");

    private String descricao;

    AmbienteVendasCliente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
