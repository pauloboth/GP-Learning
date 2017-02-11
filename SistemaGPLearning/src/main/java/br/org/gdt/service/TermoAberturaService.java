package br.org.gdt.service;

import br.org.gdt.dao.TermodeAberturaDAO;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("termoAberturaService")
public class TermoAberturaService {

    @Autowired
    private TermodeAberturaDAO dao;

    @Transactional
    public void insert(TermoAbertura item) {
        dao.insert(item);
    }

    @Transactional
    public void update(TermoAbertura item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public TermoAbertura findById(long id) {
        return dao.findById(id);
    }

    public List<TermoAbertura> findAll() {
        return dao.findAll();
    }

    public TermoAbertura findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }
}
