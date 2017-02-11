package br.org.gdt.beans;

import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import br.org.gdt.service.TurmaService;
import br.org.gdt.service.UsuarioService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class TurmaBean {

    private Turma turma = new Turma();
    @ManagedProperty("#{turmaService}")
    private TurmaService service;
    private DataModel turmas;

    private Usuario usuario = new Usuario();
    @ManagedProperty("#{usuarioService}")
    private UsuarioService userService;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TurmaBean() {
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public DataModel getTurmas() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        Usuario usuariologado = userService.findByEmail(emailuser);

        turmas = new ListDataModel(service.findbyProfessor(usuariologado));
        return turmas;
    }

    public void setTurmas(DataModel turmas) {
        this.turmas = turmas;
    }

    public String salvar() {
        if (!turma.getNomeTurma().equals("") && turma.getAno() > 0) {

            ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            String emailuser = request.getRemoteUser();
            System.out.println("" + emailuser);
            Usuario usuariologado = userService.findByEmail(emailuser);

            if (turma.getIdTurma() > 0) {
                turma.setProfessor(usuariologado);
                service.update(turma);
            } else {
                turma.setProfessor(usuariologado);
                service.insert(turma);
            }
            return "ListagemTurmas";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher o nome e ano da turma!"));
            return "ManutencaoTurmas";
        }
    }

    public String select() {
        turma = (Turma) turmas.getRowData();
        turma = service.findById(turma.getIdTurma());
        return "ManutencaoTurmas";
    }

    public String duplicar() {
        Turma copia = new Turma();
        turma = (Turma) turmas.getRowData();
        copia.setNomeTurma(turma.getNomeTurma() + " CÓPIA");
        copia.setAno(turma.getAno());
        copia.setDescricaoTelaColetarRequisitos(turma.getDescricaoTelaColetarRequisitos());
        copia.setDescricaoTelaCriarEAP(turma.getDescricaoTelaCriarEAP());
        copia.setDescricaoTelaCriarPlanoGerenciamentoEscopo(turma.getDescricaoTelaCriarPlanoGerenciamentoEscopo());
        copia.setDescricaoTelaCriarPlanoGerenciamentoProjeto(turma.getDescricaoTelaCriarPlanoGerenciamentoProjeto());
        copia.setDescricaoTelaDefinirEscopo(turma.getDescricaoTelaDefinirEscopo());
        copia.setDescricaoTelaGrupoProcessosIniciacao(turma.getDescricaoTelaGrupoProcessosIniciacao());
        copia.setDescricaoTelaGrupoProcessosPlanejamento(turma.getDescricaoTelaGrupoProcessosPlanejamento());
        copia.setDescricaoTelaInicialGerenciamentoProjetos(turma.getDescricaoTelaInicialGerenciamentoProjetos());
        copia.setDescricaoTelaPartesInteressadas(turma.getDescricaoTelaPartesInteressadas());
        copia.setDescricaoTelaTermoAberturaCronogramaMarcos(turma.getDescricaoTelaTermoAberturaCronogramaMarcos());
        copia.setDescricaoTelaTermoAberturaDescricao(turma.getDescricaoTelaTermoAberturaDescricao());
        copia.setDescricaoTelaTermoAberturaJustificativa(turma.getDescricaoTelaTermoAberturaJustificativa());
        copia.setDescricaoTelaTermoAberturaPremissas(turma.getDescricaoTelaTermoAberturaPremissas());
        copia.setDescricaoTelaTermoAberturaRequisitos(turma.getDescricaoTelaTermoAberturaRequisitos());
        copia.setDescricaoTelaTermoAberturaRestricoes(turma.getDescricaoTelaTermoAberturaRestricoes());
        copia.setProfessor(turma.getProfessor());
        service.insert(copia);
        return "ListagemTurmas";
    }

    public String excluir() throws Exception {
        try {
            turma = (Turma) turmas.getRowData();
            service.delete(turma.getIdTurma());
            turmas = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "ListagemTurmas";
    }

    public String novaTurma() {
        turma = new Turma();
        return "ManutencaoTurmas";
    }

}
