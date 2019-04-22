package br.com.SGP.utils;

public enum ClassificacaoClienteABC {
    TOP("TOP"),
    A("A"),
    B("B"),
    C("C");

    private String descricao;

    ClassificacaoClienteABC(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
