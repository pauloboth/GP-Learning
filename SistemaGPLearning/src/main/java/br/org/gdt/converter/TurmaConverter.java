package br.org.gdt.converter;

import br.org.gdt.model.Turma;
import br.org.gdt.service.TurmaService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("turmaConverter")
@ManagedBean
public class TurmaConverter implements Converter {

    @ManagedProperty("#{turmaService}")
    private TurmaService turmaService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return turmaService.findById(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return "" + ((Turma) o).getIdTurma();
    }

    public TurmaService getTurmaService() {
        return turmaService;
    }

    public void setTurmaService(TurmaService turmaService) {
        this.turmaService = turmaService;
    }
}
