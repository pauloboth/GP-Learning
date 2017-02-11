package br.org.gdt.service;

import br.org.gdt.dao.PerfilDAO;
import br.org.gdt.model.Perfil;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfilService")
public class PerfilService {

    @Autowired
    private PerfilDAO dao;

    @Transactional
    public void insert(Perfil item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Perfil item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Perfil findById(long id) {
        return dao.findById(id);
    }

    public List<Perfil> findAll() {
        return dao.findAll();
    }

    public Perfil findByUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }
}
