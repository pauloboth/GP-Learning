package br.org.gdt.beans;

import br.org.gdt.model.Restricao;
import br.org.gdt.service.RestricaoService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class RestricaoBean {

    private Restricao item = new Restricao();
    @ManagedProperty("#{restricaoService}")
    private RestricaoService service;
    private DataModel restricoes;

    public RestricaoBean() {
    }

    public Restricao getRestricao() {
        return item;
    }

    public void setRestricao(Restricao item) {
        this.item = item;
    }

    public DataModel getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(DataModel restricoes) {
        this.restricoes = restricoes;
    }

    public String salvar() {

        if (item.getIdRestricao() > 0) {
            service.update(item);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(item);
        }
        return "RestricoesTermoAbertura";
    }

    public String select() {
        item = (Restricao) restricoes.getRowData();
        item = service.findById(item.getIdRestricao());
        return "RestricoesTermoAbertura";
    }

    public String excluir() {
        item = (Restricao) restricoes.getRowData();
        service.delete(item.getIdRestricao());
        restricoes = null;
        return "RestricoesTermoAbertura";
    }

    public String MarcosTermoAbertura() {
        item = new Restricao();
        return "RestricoesTermoAbertura";
    }

}
