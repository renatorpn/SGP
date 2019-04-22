package br.com.SGP.utils;

public enum CategoriaCliente {
    DIAMANTE("Diamante"),
    OURO("Ouro"),
    PRATA("Prata"),
    BRONZE("Bronze");

    private String descricao;

    CategoriaCliente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
