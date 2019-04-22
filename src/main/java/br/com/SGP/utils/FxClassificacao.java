package br.com.SGP.utils;

public enum FxClassificacao {
    P1("P1"),
    P2("P2"),
    P3("P3"),
    P4("P4"),
    P5("P5");

    private String descricao;

    FxClassificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
