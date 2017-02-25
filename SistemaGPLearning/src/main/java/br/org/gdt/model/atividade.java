/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mateus
 */
public class atividade {
    
     @SequenceGenerator(name = "genperfil", sequenceName = "seqperfil", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genperfil")
    @Id
     @Column("atv_id")
    private int id;
     
    @Column("atv_vnome")
   private String nome;
    @Column("atv_dcriacao")
   private Date Criacao;
    @Column("atv_dtermino")
   private Date termino;
    @Column("atv_dconclusao")
   private Date conclusao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCriacao() {
        return Criacao;
    }

    public void setCriacao(Date Criacao) {
        this.Criacao = Criacao;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }
   
   
}
