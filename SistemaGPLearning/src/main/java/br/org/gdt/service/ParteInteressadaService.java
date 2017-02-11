package br.org.gdt.service;

import br.org.gdt.dao.ParteInteressadaDAO;
import br.org.gdt.model.ParteInteressada;
import br.org.gdt.model.Projeto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("parteInteressadaService")
public class ParteInteressadaService {

    @Autowired
    private ParteInteressadaDAO dao;

    @Transactional
    public void insert(ParteInteressada item) {
        dao.insert(item);
    }

    @Transactional
    public void update(ParteInteressada item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public ParteInteressada findById(long id) {
        return dao.findById(id);
    }

    public List<ParteInteressada> findAll() {
        return dao.findAll();
    }

    public List<ParteInteressada> findByProjeto(Projeto projeto) {
        return dao.findByProjeto(projeto);
    }
}
