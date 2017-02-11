package br.org.gdt.beans;

import br.org.gdt.model.TermoAbertura;
import br.org.gdt.service.TermoAberturaService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

@ManagedBean
@SessionScoped
public class TermodeAberturaBean {

    private TermoAbertura item = new TermoAbertura();
    @ManagedProperty("#{termoAberturaService}")
    private TermoAberturaService service;
    private DataModel termosdeabertura;

    public TermodeAberturaBean() {
    }

    public TermoAbertura getTermodeabertura() {
        return item;
    }

    public void setTermodeabertura(TermoAbertura item) {
        this.item = item;
    }

    public DataModel getTermosdeabertura() {
        return termosdeabertura;
    }

    public void setTermosdeabertura(DataModel termosdeabertura) {
        this.termosdeabertura = termosdeabertura;
    }

    public String DescreverProjeto() {
        service.update(item);
        return "PremissasTermoAbertura";
    }

    public String select() {
        item = (TermoAbertura) termosdeabertura.getRowData();
        item = service.findById(item.getIdTermoAbertura());
        return "manuttermosdeabertura";
    }

}
