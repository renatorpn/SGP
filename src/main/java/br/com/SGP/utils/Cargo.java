package br.com.SGP.utils;

public enum Cargo {
    ASSISTENTE("Assistente Comercial"),
    GERENTE("Gerente Comercial"),
    REPRESENTANTE("Representante Comercial"),
    ADM("Administrador do Sistema");

    private String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
