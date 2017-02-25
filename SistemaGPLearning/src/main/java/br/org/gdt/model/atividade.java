/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.util.Date;

/**
 *
 * @author Mateus
 */
public class atividade {
   private String nome;
   private Date Criacao;
   private Date termino;
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
