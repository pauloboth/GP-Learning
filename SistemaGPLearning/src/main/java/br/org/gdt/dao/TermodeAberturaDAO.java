package br.org.gdt.dao;

import br.org.gdt.model.Marco;
import br.org.gdt.model.ParteInteressada;
import br.org.gdt.model.Premissa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.Restricao;
import br.org.gdt.model.TermoAbertura;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("termodeAberturaDAO")
public class TermodeAberturaDAO extends DAO<TermoAbertura> {

    public TermodeAberturaDAO() {
        classe = TermoAbertura.class;
    }

    public TermoAbertura findByIdRelatorio(int id) {
        TermoAbertura tabertura = (TermoAbertura) entityManager.createQuery("from TermoAbertura as ta where ta.idTermoAbertura = :p")
                .setParameter("p", id).getSingleResult();

        System.out.println("Buscando por relatórios, entrando em marcos" + tabertura.getMarcos());

        List<Marco> marcos = new ArrayList<Marco>();
        for (Marco u : tabertura.getMarcos()) {
            marcos.add(u);
        }
        tabertura.setMarcos(marcos);

        System.out.println("Buscando por relatórios, entrando em premissas" + tabertura.getPremissas());
        List<Premissa> premissas = new ArrayList<Premissa>();
        for (Premissa u : tabertura.getPremissas()) {
            premissas.add(u);
        }
        tabertura.setPremissas(premissas);

        System.out.println("Buscando por relatórios, entrando em restrições" + tabertura.getRestricoes());
        List<Restricao> restricoes = new ArrayList<Restricao>();
        for (Restricao u : tabertura.getRestricoes()) {
            restricoes.add(u);
        }
        tabertura.setRestricoes(restricoes);

        System.out.println("Buscando por relatórios, entrando em requisitos" + tabertura.getRequisitosTermoAberturas());
        List<RequisitoTermoAbertura> reqta = new ArrayList<RequisitoTermoAbertura>();
        for (RequisitoTermoAbertura u : tabertura.getRequisitosTermoAberturas()) {
            reqta.add(u);
        }
        tabertura.setRequisitosTermoAberturas(reqta);

        Projeto projeto = (Projeto) entityManager.createQuery("from Projeto where termoabertura=:p")
                .setParameter("p", tabertura).getSingleResult();

        System.out.println("Projeto: " + projeto.getNomeProjeto());
        System.out.println("Partes do Projeto" + projeto.getPartesinteressadas().size());

        System.out.println("Buscando por relatórios, entrando em requisitos" + tabertura.getProjeto().getPartesinteressadas());
        List<ParteInteressada> partesinteressadas = new ArrayList<ParteInteressada>();

        for (ParteInteressada p : projeto.getPartesinteressadas()) {
            partesinteressadas.add(p);
            System.out.println("item" + p);
        }
        projeto.setPartesinteressadas(partesinteressadas);
        tabertura.setProjeto(projeto);
        System.out.println("Partes " + projeto.getPartesinteressadas());

        System.out.println("Buscando por relatórios, concluído");
        return tabertura;
    }

}
