package br.org.gdt.dao;

import br.org.gdt.model.ParteInteressada;
import br.org.gdt.model.Projeto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("parteInteressadaDAO")
public class ParteInteressadaDAO extends DAO<ParteInteressada> {

    public ParteInteressadaDAO() {
        classe = ParteInteressada.class;
    }

    public List<ParteInteressada> findByProjeto(Projeto projeto) {
        return entityManager.createQuery("from ParteInteressada where projeto = :p")
                .setParameter("p", projeto)
                .getResultList();
    }
}
