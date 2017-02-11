package br.org.gdt.beans;

import br.org.gdt.model.ParteInteressada;
import br.org.gdt.service.ParteInteressadaService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class ParteInteressadaBean {

    public ParteInteressada item = new ParteInteressada();
    @ManagedProperty("#{parteInteressadaService}")
    private ParteInteressadaService service;
    public DataModel partesinteressadas;

    public ParteInteressadaBean() {
    }

    public ParteInteressada getParteinteressada() {
        return item;
    }

    public void setParteinteressada(ParteInteressada item) {
        this.item = item;
    }

    public DataModel getPartesinteressadas() {
        return partesinteressadas;
    }

    public void setPartesinteressadas(DataModel partesinteressadas) {
        this.partesinteressadas = partesinteressadas;
    }

    public String salvar() {
        if (item.getIdParteInteressada() > 0) {
            service.update(item);
        } else {
            service.insert(item);
        }
        return "PartesInteressadas";
    }

    public String select() {
        item = (ParteInteressada) partesinteressadas.getRowData();
        item = service.findById(item.getIdParteInteressada());
        return "PartesInteressadas";
    }

    public String delete() {
        item = (ParteInteressada) partesinteressadas.getRowData();
        service.delete(item.getIdParteInteressada());
        partesinteressadas = null;
        return "PartesInteressadas";
    }

    public String NovaParteInteressada() {
        item = new ParteInteressada();
        return "PartesInteressadas";
    }

}
