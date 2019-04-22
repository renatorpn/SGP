package br.com.SGP.utils;

public enum CategoriaProdutoEnum {
    ACTIVE("Active"), 
    FITNESS("Fitness"), 
    RUNNING("Running"), 
    CLASSICO("Classico"), 
    LIFESTYLE("Lifestyle"), 
    KIDS("Kids"), 
    CASUAL("Casual"), 
    TRAINING("Training"), 
    VESTUARIO("Vestu�rio"), 
    ACESSORIO("Acess�rio");

    private String descricao;

    CategoriaProdutoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
