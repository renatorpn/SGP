package br.com.SGP.utils;

public enum TipoEvento {
   SHOWROOM("Showroom"), 
   REUNIAO("Reunião"), 
   ALMOCO ("Almoço"), 
   CONVENCAO ("Convenção"), 
   FEIRA_REGIONAL("Feira regional"), 
   CAMPANHA_DE_VENDA("Campanha de Venda"), 
   ACAO_DE_MARKETING("Ação de Marketing");

    private String descricao;

    TipoEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
