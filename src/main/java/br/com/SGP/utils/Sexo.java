package br.com.SGP.utils;

public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    UNISSEX("Unissex");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
