package br.org.gdt.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Projeto implements Serializable {

    @SequenceGenerator(name = "genprojeto", sequenceName = "seqprojeto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genprojeto")
    @Id
    private int idProjeto;
    
    private String nomeProjeto;
    @Column(length = 2500)
    private String descricaoGeral;
    @Column(length = 2500)
    private String comentarioGeralInstrutor;
    private String empresa;
    @Column(length = 2500)
    private String ComentarioRequisitos;
    @Column(length = 2500)
    private String ComentarioRestricoes;
    @Column(length = 2500)
    private String ComentarioMarcos;
    @Column(length = 2500)
    private String ComentarioPremissas;
    @Column(length = 2500)
    private String ComentarioInfoBasicasTA;
    @Column(length = 2500)
    private String comentarioRequisitosTermoAbertura;
    @Column(length = 2500)
    private String comentarioPartesInteressadas;

    @OneToMany(mappedBy = "projeto")
    private List<ParteInteressada> partesinteressadas;

    @ManyToOne
    private Turma turmadoprojeto;

    @OneToMany(mappedBy = "projeto")
    private List<Requisito> requisitos;

    @ManyToOne
    private Usuario gerenteProjeto;

    @ManyToMany
    private List<Usuario> componentes;

    private String dataCriacao;
    private String dataModificacao;
    private String estado;
    @Column(length = 2500)
    private String comentarioPlanoGereProjeto;
    @Column(length = 2500)
    private String planoGereEscopo;
    @Column(length = 2500)
    private String comentarioPlanoGereEscopo;
    @Column(length = 2500)
    private String escopo;
    @Column(length = 2500)
    private String comentarioEscopo;
    private byte[] EAP;
    @Column(length = 2500)
    private String comentarioEAP;

    @OneToOne
    private TermoAbertura termoabertura;

    public Projeto() {
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricaoGeral() {
        return descricaoGeral;
    }

    public void setDescricaoGeral(String descricaoGeral) {
        this.descricaoGeral = descricaoGeral;
    }

    public String getComentarioGeralInstrutor() {
        return comentarioGeralInstrutor;
    }

    public void setComentarioGeralInstrutor(String comentarioGeralInstrutor) {
        this.comentarioGeralInstrutor = comentarioGeralInstrutor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getComentarioRequisitos() {
        return ComentarioRequisitos;
    }

    public void setComentarioRequisitos(String ComentarioRequisitos) {
        this.ComentarioRequisitos = ComentarioRequisitos;
    }

    public String getComentarioRestricoes() {
        return ComentarioRestricoes;
    }

    public void setComentarioRestricoes(String ComentarioRestricoes) {
        this.ComentarioRestricoes = ComentarioRestricoes;
    }

    public String getComentarioMarcos() {
        return ComentarioMarcos;
    }

    public void setComentarioMarcos(String ComentarioMarcos) {
        this.ComentarioMarcos = ComentarioMarcos;
    }

    public String getComentarioPremissas() {
        return ComentarioPremissas;
    }

    public void setComentarioPremissas(String ComentarioPremissas) {
        this.ComentarioPremissas = ComentarioPremissas;
    }

    public String getComentarioInfoBasicasTA() {
        return ComentarioInfoBasicasTA;
    }

    public void setComentarioInfoBasicasTA(String ComentarioInfoBasicasTA) {
        this.ComentarioInfoBasicasTA = ComentarioInfoBasicasTA;
    }

    public String getComentarioRequisitosTermoAbertura() {
        return comentarioRequisitosTermoAbertura;
    }

    public void setComentarioRequisitosTermoAbertura(String comentarioRequisitosTermoAbertura) {
        this.comentarioRequisitosTermoAbertura = comentarioRequisitosTermoAbertura;
    }

    public String getComentarioPartesInteressadas() {
        return comentarioPartesInteressadas;
    }

    public void setComentarioPartesInteressadas(String comentarioPartesInteressadas) {
        this.comentarioPartesInteressadas = comentarioPartesInteressadas;
    }

    public List<ParteInteressada> getPartesinteressadas() {
        return partesinteressadas;
    }

    public void setPartesinteressadas(List<ParteInteressada> partesinteressadas) {
        this.partesinteressadas = partesinteressadas;
    }

    public Turma getTurmadoprojeto() {
        return turmadoprojeto;
    }

    public void setTurmadoprojeto(Turma turmadoprojeto) {
        this.turmadoprojeto = turmadoprojeto;
    }



    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public Usuario getGerenteProjeto() {
        return gerenteProjeto;
    }

    public void setGerenteProjeto(Usuario gerenteProjeto) {
        this.gerenteProjeto = gerenteProjeto;
    }

    public List<Usuario> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Usuario> componentes) {
        this.componentes = componentes;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarioPlanoGereProjeto() {
        return comentarioPlanoGereProjeto;
    }

    public void setComentarioPlanoGereProjeto(String comentarioPlanoGereProjeto) {
        this.comentarioPlanoGereProjeto = comentarioPlanoGereProjeto;
    }

    public String getPlanoGereEscopo() {
        return planoGereEscopo;
    }

    public void setPlanoGereEscopo(String planoGereEscopo) {
        this.planoGereEscopo = planoGereEscopo;
    }

    public String getComentarioPlanoGereEscopo() {
        return comentarioPlanoGereEscopo;
    }

    public void setComentarioPlanoGereEscopo(String comentarioPlanoGereEscopo) {
        this.comentarioPlanoGereEscopo = comentarioPlanoGereEscopo;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public String getComentarioEscopo() {
        return comentarioEscopo;
    }

    public void setComentarioEscopo(String comentarioEscopo) {
        this.comentarioEscopo = comentarioEscopo;
    }

    public byte[] getEAP() {
        return EAP;
    }

    public void setEAP(byte[] EAP) {
        this.EAP = EAP;
    }

    public String getComentarioEAP() {
        return comentarioEAP;
    }

    public void setComentarioEAP(String comentarioEAP) {
        this.comentarioEAP = comentarioEAP;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idProjeto;
        hash = 97 * hash + Objects.hashCode(this.nomeProjeto);
        hash = 97 * hash + Objects.hashCode(this.descricaoGeral);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if (this.idProjeto != other.idProjeto) {
            return false;
        }
        if (!Objects.equals(this.nomeProjeto, other.nomeProjeto)) {
            return false;
        }
        if (!Objects.equals(this.descricaoGeral, other.descricaoGeral)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeProjeto;
    }

}
