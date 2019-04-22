package br.com.SGP.utils;

public enum NivelAcesso {
    admin("admin"),
    readonly("readonly"),
    readwrite("readwrite"),
    manager("manager");

    private String descricao;

    NivelAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
