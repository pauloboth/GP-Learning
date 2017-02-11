package br.org.gdt.beans;

import br.org.gdt.model.Premissa;
import br.org.gdt.service.PremissaService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class PremissaBean {

    private Premissa item = new Premissa();
    @ManagedProperty("#{premissaService}")
    private PremissaService service;
    private DataModel premissas;

    public Premissa getPremissa() {
        return item;
    }

    public void setPremissa(Premissa premissa) {
        this.item = premissa;
    }

    public DataModel getPremissas() {
        return premissas;
    }

    public void setPremissas(DataModel premissas) {
        this.premissas = premissas;
    }

    public String salvar() {
        //Caso já tenha sido criado
        if (item.getIdPremissa() > 0) {
            //modifica-se somente a data de modificação
            service.update(item);
            //Se for novo registro altere as datas de criação e modificação
        } else {
            //modifica-se as duas datas
            service.insert(item);
        }
        return "PremissasTermoAbertura";
    }

    public String select() {
        item = (Premissa) premissas.getRowData();
        item = service.findById(item.getIdPremissa());
        System.out.println("Teste premissa é: " + item);
        return "PremissasTermoAbertura";
    }

    public String excluir() {
        item = (Premissa) premissas.getRowData();
        service.delete(item.getIdPremissa());
        premissas = null;
        return "PremissasTermoAbertura";
    }

    public String novaPremissa() {
        item = new Premissa();
        return "PremissasTermoAbertura";
    }

}
