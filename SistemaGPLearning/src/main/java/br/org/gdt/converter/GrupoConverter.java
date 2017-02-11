package br.org.gdt.converter;

import br.org.gdt.model.Grupo;
import br.org.gdt.service.GrupoService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("grupoConverter")
@ManagedBean
public class GrupoConverter implements Converter {

    @ManagedProperty("#{grupoService}")
    private GrupoService grupoService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return grupoService.findById(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return "" + ((Grupo) o).getIdGrupo();
    }

    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }
}
