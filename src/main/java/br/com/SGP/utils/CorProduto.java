package br.com.SGP.utils;

public enum CorProduto {
    PRETO_E_LARANJA("Preto/Laranja"),
    AZUL_E_VERDE("Azul/Verde"),
    PRETO_E_VERMELHO("Preto/Vermelho"),
    AZUL_MARINHO("Azul/Marinho"),
    PRETO("Preto"),
    BRANCO("Branco"),
    AZUL_E_ROSA("Azul/Rosa"),
    ROSA("Rosa"),
    VERDE_E_ROXO("Verde/Roxo");

    private String descricao;

    CorProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
