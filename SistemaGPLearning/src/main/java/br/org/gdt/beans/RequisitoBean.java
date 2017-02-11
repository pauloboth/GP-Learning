package br.org.gdt.beans;

import br.org.gdt.model.Requisito;
import br.org.gdt.service.RequisitoService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class RequisitoBean {

    private Requisito item = new Requisito();
    @ManagedProperty("#{requisitoService}")
    private RequisitoService service;
    private DataModel requisitos;

    public RequisitoBean() {
    }

    public Requisito getRequisito() {
        return item;
    }

    public void setRequisito(Requisito item) {
        this.item = item;
    }

    public DataModel getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(DataModel requisitos) {
        this.requisitos = requisitos;
    }

    //quando é clicado o botão inserir
    public String salvar() {
        if (item.getIdRequisito() > 0) {
            service.update(item);
        } else {
            service.insert(item);
        }
        return "listarrequisitos";
    }

    public String select() {
        item = (Requisito) requisitos.getRowData();
        item = service.findById(item.getIdRequisito());
        return "manutrequisitos";
    }

    public String delete() {
        item = (Requisito) requisitos.getRowData();
        service.delete(item.getIdRequisito());
        requisitos = null;
        return "listarrequisitos";
    }

    public String novoRequisito() {
        item = new Requisito();
        return "manutrequisitos";
    }
}
