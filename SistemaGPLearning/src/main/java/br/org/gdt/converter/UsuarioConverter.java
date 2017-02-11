package br.org.gdt.converter;

import br.org.gdt.model.Usuario;
import br.org.gdt.service.UsuarioService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("usuarioConverter")
@ManagedBean
public class UsuarioConverter implements Converter {

    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return usuarioService.findById(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return "" + ((Usuario) o).getIdUsuario();
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
