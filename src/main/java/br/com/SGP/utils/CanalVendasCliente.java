package br.com.SGP.utils;

public enum CanalVendasCliente {
    SAPATARIA("Sapataria"),
    ESPORTIVO("Esportivo"),
    VIRTUAL("Virtual"),
    MAGAZINE("Magazine"),
    BOUTIQUE("Boutique"),
    E_COMMERCE("E-Commerce"),
    OUTROS("Outros");

    private String descricao;

    CanalVendasCliente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
