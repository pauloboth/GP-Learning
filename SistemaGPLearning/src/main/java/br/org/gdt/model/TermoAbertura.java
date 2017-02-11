package br.org.gdt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TermoAbertura implements Serializable {

    @SequenceGenerator(name = "gentermodeabertura", sequenceName = "seqtermodeabertura", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gentermodeabertura")
    @Id
    private int idTermoAbertura;
    @Column(length = 2500)
    private String descricaoProjeto;
    @Column(length = 2500)
    private String justificativaProjeto;
    
    private String dataCadastro;
    private String dataModificacao;

    @OneToOne
    private Projeto projeto;

    @OneToMany(mappedBy = "termoabertura")
    private List<Premissa> premissas;

    @OneToMany(mappedBy = "termoabertura")
    private List<Restricao> restricoes;

    @OneToMany(mappedBy = "termoabertura")
    private List<Marco> marcos;

    @OneToMany(mappedBy = "termoabertura")
    private List<RequisitoTermoAbertura> requisitosTermoAberturas;

    public TermoAbertura() {
    }

    public int getIdTermoAbertura() {
        return idTermoAbertura;
    }

    public void setIdTermoAbertura(int idTermoAbertura) {
        this.idTermoAbertura = idTermoAbertura;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getJustificativaProjeto() {
        return justificativaProjeto;
    }

    public void setJustificativaProjeto(String justificativaProjeto) {
        this.justificativaProjeto = justificativaProjeto;
    }

    

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Premissa> getPremissas() {
        return premissas;
    }

    public void setPremissas(List<Premissa> premissas) {
        this.premissas = premissas;
    }

    public List<Restricao> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<Restricao> restricoes) {
        this.restricoes = restricoes;
    }

    public List<Marco> getMarcos() {
        return marcos;
    }

    public void setMarcos(List<Marco> marcos) {
        this.marcos = marcos;
    }

    public List<RequisitoTermoAbertura> getRequisitosTermoAberturas() {
        return requisitosTermoAberturas;
    }

    public void setRequisitosTermoAberturas(List<RequisitoTermoAbertura> requisitosTermoAberturas) {
        this.requisitosTermoAberturas = requisitosTermoAberturas;
    }

}
