package br.com.SGP.utils;

public enum CargoContatoCliente {
    COMPRADOR("Comprador"),
    DIRETOR("Diretor"),
    GERENTE("Gerente"),
    SUPERVISOR("Supervisor de Loja"),
    MARKETING("Marketing"),
    PROPRIETARIO("Proprietário da Rede"),
    OUTROS("Outros cargos");
    

    private String descricao;

    CargoContatoCliente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
