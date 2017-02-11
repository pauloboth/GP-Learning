package br.org.gdt.beans;

import br.org.gdt.model.Marco;
import br.org.gdt.service.MarcoService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class MarcoBean {

    private Marco item = new Marco();
    @ManagedProperty("#{marcoService}")
    private MarcoService service;
    private DataModel marcos;

    public MarcoBean() {
    }

    public Marco getMarco() {
        return item;
    }

    public void setMarco(Marco item) {
        this.item = item;
    }

    public DataModel getMarcos() {
        return marcos;
    }

    public void setMarcos(DataModel marcos) {
        this.marcos = marcos;
    }

    public String salvar() {
        //Caso já tenha sido criado
        if (item.getIdMarco() > 0) {
            //modifica-se somente a data de modificação
            service.update(item);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(item);
        }
        return "MarcosTermoAbertura";
    }

    public String select() {
        item = (Marco) marcos.getRowData();
        item = service.findById(item.getIdMarco());
        return "MarcosTermoAbertura";
    }

    public String excluir() {
        item = (Marco) marcos.getRowData();
        service.delete(item.getIdMarco());
        marcos = null;
        return "MarcosTermoAbertura";
    }

    public String novoMarco() {
        item = new Marco();
        return "MarcosTermoAbertura";
    }

}
