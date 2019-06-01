package br.com.SGP.entities;

import br.com.SGP.utils.Estado;
import br.com.SGP.utils.*;
import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Cadastro implements Serializable{
    public static final long serialVersionUID = 1L;

    
    @Id
    @SequenceGenerator(name="idCliente",
                       sequenceName="cliente_id_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idCliente")
    private Long id;

    @Size(min = 2, max = 255)
    private String nome;
    @Size(min = 2, max = 255)
    private String razaoSocial;

    @CNPJ
    @Column(unique = true)
    private String cnpj;
    
    /*
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    */
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassificacaoClienteABC classificacaoabc;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AmbienteVendasCliente ambienteVendasCliente;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaCliente categoriaCliente;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private CanalVendasCliente canalVendasCliente;
    /*
    @NotNull
    @DecimalMin("0.0")
    private Double rendaMensal;
    */
    @NotBlank
    private String logradouro;
    @NotNull
    private String numero;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    private String cep;
    
    @NotNull
    @Min(1)
    private Integer quantidadelojas;
    private String outrasInformacoes;
    
    @Column(name="logo")
    private String logomarca;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @Valid
    private List<Balanco> balanco;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<ContatoCliente> contatos;
           
    @NotNull
    @ManyToOne(optional = false)
    private Usuario representante;
    
    public List<Balanco> getBalanco() {
        return balanco;
    }

    public void setBalanco(List<Balanco> balanco) {
        this.balanco = balanco;
    }

    public List<ContatoCliente> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoCliente> contatos) {
        this.contatos = contatos;
    }

    public Usuario getRepresentante() {
        return representante;
    }

    public void setRepresentante(Usuario representante) {
        this.representante = representante;
    }

    
    

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Integer getQuantidadelojas() {
        return quantidadelojas;
    }

    public void setQuantidadelojas(Integer quantidadelojas) {
        this.quantidadelojas = quantidadelojas;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }

    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public CanalVendasCliente getCanalVendasCliente() {
        return canalVendasCliente;
    }

    public void setCanalVendasCliente(CanalVendasCliente canalVendasCliente) {
        this.canalVendasCliente = canalVendasCliente;
    }
    
    

    public AmbienteVendasCliente getAmbienteVendasCliente() {
        return ambienteVendasCliente;
    }

    public void setAmbienteVendasCliente(AmbienteVendasCliente ambienteVendasCliente) {
        this.ambienteVendasCliente = ambienteVendasCliente;
    }

    public CategoriaCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(CategoriaCliente categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

    

    public ClassificacaoClienteABC getClassificacaoabc() {
        return classificacaoabc;
    }

    public void setClassificacaoabc(ClassificacaoClienteABC classificacaoabc) {
        this.classificacaoabc = classificacaoabc;
    }
    
    

   
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cadastro other = (Cadastro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cadastro{" + "id=" + id + ", nome=" + nome + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", classificacaoabc=" + classificacaoabc + ", ambienteVendasCliente=" + ambienteVendasCliente + ", categoriaCliente=" + categoriaCliente + ", canalVendasCliente=" + canalVendasCliente + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", quantidadelojas=" + quantidadelojas + ", outrasInformacoes=" + outrasInformacoes + ", logomarca=" + logomarca + ", balanco=" + balanco + '}';
    }

       

   
}
