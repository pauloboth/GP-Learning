package br.org.gdt.beans;

import br.org.gdt.model.Grupo;
import br.org.gdt.model.Marco;
import br.org.gdt.model.ParteInteressada;
import br.org.gdt.model.Premissa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.Restricao;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import br.org.gdt.service.GrupoService;
import br.org.gdt.service.MarcoService;
import br.org.gdt.service.ParteInteressadaService;
import br.org.gdt.service.PremissaService;
import br.org.gdt.service.ProjetoService;
import br.org.gdt.service.RequisitoService;
import br.org.gdt.service.RequisitoTermoAberturaService;
import br.org.gdt.service.RestricaoService;
import br.org.gdt.service.TermoAberturaService;
import br.org.gdt.service.TurmaService;
import br.org.gdt.service.UsuarioService;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class ProjetoBean {

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoService}")
    private ProjetoService service;
    private DataModel projetos;
    private DataModel projetosporaluno;
    private List<Projeto> projetosparainstrutor;
    private List<Projeto> projetosfiltrados;
    //private List<Projeto> projetos = new ArrayList<Projeto>();

    private Usuario usuariologado;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;
    private List<Usuario> usuarios;

    private TermoAbertura termodeabertura = new TermoAbertura();
    @ManagedProperty("#{termoAberturaService}")
    private TermoAberturaService termoAberturaService;

    private Grupo grupo = new Grupo();
    private GrupoService GrupoService;

    private Premissa premissa = new Premissa();
    @ManagedProperty("#{premissaService}")
    private PremissaService premissaService;
    private DataModel premissas;

    private Restricao restricao = new Restricao();
    @ManagedProperty("#{restricaoService}")
    private RestricaoService restricaoService;
    private DataModel restricoes;

    private Marco marco = new Marco();
    @ManagedProperty("#{marcoService}")
    private MarcoService marcoService;
    private DataModel marcos;

    private RequisitoTermoAbertura reqta = new RequisitoTermoAbertura();
    @ManagedProperty("#{requisitoTermoAberturaService}")
    private RequisitoTermoAberturaService requisitoTermoAberturaService;
    private DataModel reqtosta;

    private ParteInteressada parteinteressada = new ParteInteressada();
    @ManagedProperty("#{parteInteressadaService}")
    private ParteInteressadaService parteInteressadaService;
    private DataModel partesinteressadas;

    private Requisito requisito = new Requisito();
    @ManagedProperty("#{requisitoService}")
    private RequisitoService requisitoService;
    private DataModel requisitos;

    private UploadedFile fileEAP;

    private Turma turma;
    private List<Turma> turmasdousuario;

    public List<Projeto> getProjetosparainstrutor() {
        return projetosparainstrutor;
    }

    public void setProjetosparainstrutor(List<Projeto> projetosparainstrutor) {
        this.projetosparainstrutor = projetosparainstrutor;
    }

    public ProjetoBean() {
    }

    public List<Usuario> getUsuarios() {
        Usuario userlogado = usuariologado;
        System.out.println("Usuário logado é " + usuariologado.getNome());
        turma = userlogado.getTurma();
        grupo = userlogado.getGrupo();
        System.out.println("Carregando usuários...");
        usuarios = usuarioService.findByUsers(turma, grupo);

        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuariologado() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        usuariologado = usuarioService.findByEmail(emailuser);
        return usuariologado;
    }

    public DataModel getProjetos() {
        //Obtenção do usuário logado
        Usuario usuariolocal = usuariologado;
        System.out.println("O usuário logado no get projetos é" + usuariolocal.getNome());
        //Verificação se usuário é administrador, então filtre pelas turmas do professor
        if (usuariolocal.getGrupo().getIdGrupo() == 1) {
//            List<Usuario> alunosdasturmas = new ArrayList<>();
//            List<Turma> turmasdoprofessor = new ArrayList<>();
//            turmasdoprofessor = usuariologado.getTurmasprofessor();
//            //Uma turma do tipo Turma, será preenchida com os objetos da datamodel turmas
//            for (Turma turma : turmasdoprofessor) {
//                alunosdasturmas.addAll(turma.getAcademicos());
//            }
//
//            List<Projeto> projetosdosalunosdasturmas = new ArrayList<>();
//            for (Usuario usuariosdalista : alunosdasturmas) {
//                projetosdosalunosdasturmas.addAll(usuariosdalista.getProjetos());
//            }
            System.out.println("Entrou no Grupo 1");
            //Pesquisaremos de acordo com a turma selecionada
            //System.out.println("Turma Selecionada no combobox" + turma.getNomeTurma());
            projetos = new ListDataModel(service.findbyturmasprofessor(usuariolocal));
            //projetos = (DataModel) projetosdosalunosdasturmas;
            //Caso não seja administrador, filtre por aluno
        } else {
            System.out.println("Entrou No grupo 2");
            //Queremos a listagem de projetos que aparecem o usuario como componente
            projetos = new ListDataModel(service.findbyaluno(usuariolocal));
        }
        return projetos;
    }

    public void setProjetos(DataModel projetos) {
        this.projetos = projetos;
    }

    public TermoAbertura getTermodeabertura() {
        termodeabertura = (TermoAbertura) projeto.getTermoabertura();
        return termodeabertura;
    }

    public void setTermodeabertura(TermoAbertura termodeabertura) {
        this.termodeabertura = termodeabertura;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Premissa getPremissa() {
        return premissa;
    }

    public void setPremissa(Premissa premissa) {
        this.premissa = premissa;
    }

    public DataModel getPremissas() {
        premissas = new ListDataModel(premissaService.findbytermoabertura(projeto.getTermoabertura()));
        return premissas;
    }

    public void setPremissas(DataModel premissas) {
        this.premissas = premissas;
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public void setRestricao(Restricao restricao) {
        this.restricao = restricao;
    }

    public DataModel getRestricoes() {
        restricoes = new ListDataModel(restricaoService.findbytermoabertura(projeto.getTermoabertura()));
        return restricoes;
    }

    public void setRestricoes(DataModel restricoes) {
        this.restricoes = restricoes;
    }

    public Marco getMarco() {
        return marco;
    }

    public void setMarco(Marco marco) {
        this.marco = marco;
    }

    public DataModel getMarcos() {
        marcos = new ListDataModel(marcoService.findbytermoabertura(projeto.getTermoabertura()));
        return marcos;
    }

    public void setMarcos(DataModel marcos) {
        this.marcos = marcos;
    }

    public RequisitoTermoAbertura getReqta() {
        return reqta;
    }

    public void setReqta(RequisitoTermoAbertura reqta) {
        this.reqta = reqta;
    }

    public DataModel getReqtosta() {
        reqtosta = new ListDataModel(requisitoTermoAberturaService.findbytermoabertura(projeto.getTermoabertura()));
        return reqtosta;
    }

    public void setReqtosta(DataModel reqtosta) {
        this.reqtosta = reqtosta;
    }

    public ParteInteressada getParteinteressada() {
        return parteinteressada;
    }

    public void setParteinteressada(ParteInteressada parteinteressada) {
        this.parteinteressada = parteinteressada;
    }

    public DataModel getPartesinteressadas() {
        partesinteressadas = new ListDataModel(parteInteressadaService.findByProjeto(projeto));
        return partesinteressadas;
    }

    public void setPartesinteressadas(DataModel partesinteressadas) {
        this.partesinteressadas = partesinteressadas;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public DataModel getRequisitos() {
        requisitos = new ListDataModel(requisitoService.findByProjeto(projeto));
        return requisitos;
    }

    public void setRequisitos(DataModel requisitos) {
        this.requisitos = requisitos;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String salvarPlanoGereEscopo() {
        if (!projeto.getPlanoGereEscopo().equals("")) {
            projeto.setDataModificacao(getDateTime());
            projeto.setEstado("Planejamento");
            service.update(projeto);
            return "ProcessosPlanejamento";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para seguir, é necessário descrever o plano de gerenciamento de escopo"));
            return "CriarPlanoGerenciamentoEscopo";
        }
    }

    public String salvarEscopo() {
        if (!projeto.getEscopo().equals("")) {
            projeto.setDataModificacao(getDateTime());
            service.update(projeto);
            return "ProcessosPlanejamento";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para seguir, é necessário descrever o escopo!"));
            return "DefinirEscopo";
        }
    }

    public String salvarComentarios() {
        projeto.setDataModificacao(getDateTime());
        service.update(projeto);
        return "ListagemProjetosInstrutor";
    }

    //quando é clicado o botão inserir
    public String salvar() {
        if (!projeto.getDescricaoGeral().equals("") && !projeto.getEmpresa().equals("") && !projeto.getNomeProjeto().equals("")) {
            //Caso já tenha sido criado
            if (projeto.getIdProjeto() > 0) {
                projeto.setDataModificacao(getDateTime());
                service.update(projeto);

            } else {
                Usuario userlogado = usuariologado;
                projeto.setTurmadoprojeto(userlogado.getTurma());
                projeto.setDataModificacao(getDateTime());
                projeto.setDataCriacao(getDateTime());
                projeto.setEstado("Iniciação");
                service.insert(projeto);
            }
            return "ListagemProjetos";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário que todos os campos sejam preenchidos!"));
            return "TermoAberturaDescricao";
        }
    }

    public String select() {
        if (usuariologado.getStatus().equals("Inativo")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "ListagemProjetos";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = service.findById(projeto.getIdProjeto());
            return "ManutencaoProjetos";
        }

    }

    public void selecionar() {
        projeto = (Projeto) projetos.getRowData();
        projeto = service.findById(projeto.getIdProjeto());
    }

    public String delete() {
        projeto = (Projeto) projetos.getRowData();
        service.delete(projeto.getIdProjeto());
        projetos = null;
        return "ListagemProjetos";
    }

    public String acessar() {
        if (usuariologado.getStatus().equals("Inativo")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "ListagemProjetos";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = service.findById(projeto.getIdProjeto());
            if (projeto.getTermoabertura() == null) {
                return "ExplicacaoGerenciamentoProjeto";
            } else {
                return "ProcessosIniciacao";
            }
        }

    }

    public String acessarComentarios() {
        projeto = (Projeto) projetos.getRowData();
        projeto = service.findById(projeto.getIdProjeto());
        return "ComentarProjetos";
    }

    public String novoProjeto() {
        if (usuariologado.getStatus().equals("Inativo")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "ListagemProjetos";
        } else {
            projeto = new Projeto();
            return "ManutencaoProjetos";
        }
    }

    public String salvarJustificativa() {
        if (!termodeabertura.getJustificativaProjeto().equals("")) {
            termoAberturaService.update(termodeabertura);
            return "PremissasTermoAbertura";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para Seguir, você deve preencher a justificativa!"));
            return "JustificativaTermoAbertura";
        }
    }

    public String salvarDescricao() {
        if (!termodeabertura.getDescricaoProjeto().equals("")) {
            termoAberturaService.update(termodeabertura);
            return "JustificativaTermoAbertura";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para Seguir, você deve preencher a descrição do projeto!"));
            return "TermoAberturaDescricao";
        }

    }

    public String salvarPremissa() {
        if (!premissa.getDescricaoPremissa().equals("")) {
            if (premissa.getIdPremissa() > 0) {
                premissaService.update(premissa);
                premissa = new Premissa();
                return "PremissasTermoAbertura";
            } else {
                termodeabertura = (TermoAbertura) projeto.getTermoabertura();
                premissa.setTermoabertura(termodeabertura);
                premissaService.insert(premissa);
                premissa = new Premissa();
                return "PremissasTermoAbertura";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para Salvar, você deve preencher a descrição da Premissa!"));
            return "PremissasTermoAbertura";
        }
    }

    public String selectPremissa() {
        premissa = (Premissa) premissas.getRowData();
        premissa = premissaService.findById(premissa.getIdPremissa());
        return "PremissasTermoAbertura";
    }

    public String excluirPremissa() {
        premissa = (Premissa) premissas.getRowData();
        premissaService.delete(premissa.getIdPremissa());
        premissa = new Premissa();
        return "PremissasTermoAbertura";
    }

    public String novaPremissa() {
        premissa = new Premissa();
        return "PremissasTermoAbertura";
    }

    public String ConcluirPremissas() {
        return "RestricoesTermoAbertura";
    }

    public String salvarRestricao() {
        if (!restricao.getDescricaoRestricao().equals("")) {
            if (restricao.getIdRestricao() > 0) {
                restricaoService.update(restricao);
                restricao = new Restricao();
                return "RestricoesTermoAbertura";
            } else {
                termodeabertura = (TermoAbertura) projeto.getTermoabertura();
                restricao.setTermoabertura(termodeabertura);
                restricaoService.insert(restricao);
                restricao = new Restricao();
                return "RestricoesTermoAbertura";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, você deve preencher a descrição da Restrição!"));
            return "RestricoesTermoAbertura";
        }
    }

    public String selectRestricao() {
        restricao = (Restricao) restricoes.getRowData();
        restricao = restricaoService.findById(restricao.getIdRestricao());
        return "RestricoesTermoAbertura";
    }

    public String excluirRestricao() {
        restricao = (Restricao) restricoes.getRowData();
        restricaoService.delete(restricao.getIdRestricao());
        restricao = new Restricao();
        return "RestricoesTermoAbertura";
    }

    public String novaRestricao() {
        restricao = new Restricao();
        return "RestricoesTermoAbertura";
    }

    public String ConcluirRestricoes() {
        return "MarcosTermoAbertura";
    }

    public String salvarMarco() {
        if (!marco.getObjetivo().equals("") && !marco.getDataPrevista().equals("")) {

            if (marco.getIdMarco() > 0) {
                System.out.println("Entrou em updateMarco");
                marco.setTermoabertura(projeto.getTermoabertura());
                marcoService.update(marco);
                marco = new Marco();
                return "MarcosTermoAbertura";
            } else {
                termodeabertura = (TermoAbertura) projeto.getTermoabertura();
                marco.setTermoabertura(termodeabertura);
                marcoService.insert(marco);
                marco = new Marco();
                return "MarcosTermoAbertura";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher os campos em branco!"));
            return "MarcosTermoAbertura";
        }

    }

    public String selectMarco() {
        marco = (Marco) marcos.getRowData();
        marco = marcoService.findById(marco.getIdMarco());
        System.out.println("selecionado marco" + marco.getObjetivo());
        return "MarcosTermoAbertura";
    }

    public String excluirMarco() {
        marco = (Marco) marcos.getRowData();
        marcoService.delete(marco.getIdMarco());
        marco = new Marco();
        return "MarcosTermoAbertura";
    }

    public String novoMarco() {
        marco = new Marco();
        return "MarcosTermoAbertura";
    }

    public String ConcluirMarcos() {
        return "RequisitosTermoAbertura";
    }

    public String salvarRequisitoTermoAbertura() {
        if (!reqta.getDescricaorequisito().equals("")) {
            if (reqta.getIdrequisitotermoabertura() > 0) {
                requisitoTermoAberturaService.update(reqta);
                reqta = new RequisitoTermoAbertura();
                return "RequisitosTermoAbertura";
            } else {
                termodeabertura = (TermoAbertura) projeto.getTermoabertura();
                reqta.setTermoabertura(termodeabertura);
                requisitoTermoAberturaService.insert(reqta);
                reqta = new RequisitoTermoAbertura();
                return "RequisitosTermoAbertura";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher a descrição do requisito!"));
            return "RequisitosTermoAbertura";
        }
    }

    public String selectRequisitoTermoAbertura() {
        reqta = (RequisitoTermoAbertura) reqtosta.getRowData();
        reqta = requisitoTermoAberturaService.findById(reqta.getIdrequisitotermoabertura());
        return "RequisitosTermoAbertura";
    }

    public String excluirRequisitoTermoAbertura() {
        reqta = (RequisitoTermoAbertura) reqtosta.getRowData();
        requisitoTermoAberturaService.delete(reqta.getIdrequisitotermoabertura());
        reqta = new RequisitoTermoAbertura();
        return "RequisitosTermoAbertura";
    }

    public String novoRequisitoTermoAbertura() {
        reqta = new RequisitoTermoAbertura();
        return "RequisitosTermoAbertura";
    }

    public String salvarParteInteressada() {
        if (!parteinteressada.getNomeParteInteressada().equals("") && !parteinteressada.getPapelProjeto().equals("") && !parteinteressada.getContribuicaoProjeto().equals("")) {
            if (parteinteressada.getIdParteInteressada() > 0) {
                parteInteressadaService.update(parteinteressada);
                parteinteressada = new ParteInteressada();
                return "IdentificarPartesInteressadas";
            } else {
                projeto = (Projeto) service.findById(projeto.getIdProjeto());
                parteinteressada.setProjeto(projeto);
                parteInteressadaService.insert(parteinteressada);
                parteinteressada = new ParteInteressada();
                return "IdentificarPartesInteressadas";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "IdentificarPartesInteressadas";
        }
    }

    public String salvarEAP() {

        byte[] imgDataBa = new byte[(int) fileEAP.getSize()];
        DataInputStream dataIs;
        try {
            dataIs = new DataInputStream(fileEAP.getInputstream());
            dataIs.readFully(imgDataBa);
        } catch (IOException ex) {
            Logger.getLogger(ProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("" + fileEAP.getFileName() + fileEAP.getSize());
        projeto = (Projeto) service.findById(projeto.getIdProjeto());
        projeto.setEAP(imgDataBa);
        System.out.println("content da file eap" + imgDataBa);
        service.update(projeto);
        return "CriarEAP?faces-redirect=true";
    }

    public String selectParteInteressada() {
        parteinteressada = (ParteInteressada) partesinteressadas.getRowData();
        parteinteressada = parteInteressadaService.findById(parteinteressada.getIdParteInteressada());
        return "IdentificarPartesInteressadas";
    }

    public String excluirParteInteressada() {
        parteinteressada = (ParteInteressada) partesinteressadas.getRowData();
        parteInteressadaService.delete(parteinteressada.getIdParteInteressada());
        parteinteressada = new ParteInteressada();
        return "IdentificarPartesInteressadas";
    }

    public String salvarRequisito() {
        if (!requisito.getDescricaoRequisito().equals("") && !requisito.getNomeRequisito().equals("")) {
            if (requisito.getIdRequisito() > 0) {
                requisitoService.update(requisito);
                requisito = new Requisito();
                return "ColetarRequisitos";
            } else {
                projeto = (Projeto) service.findById(projeto.getIdProjeto());
                requisito.setProjeto(projeto);
                requisitoService.insert(requisito);
                requisito = new Requisito();
                return "ColetarRequisitos";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, todos Campos Devem Estar Preenchidos"));
            return "ColetarRequisitos";
        }

    }

    public String selectRequisito() {
        requisito = (Requisito) requisitos.getRowData();
        requisito = requisitoService.findById(requisito.getIdRequisito());
        return "ColetarRequisitos";
    }

    public String excluirRequisito() {
        requisito = (Requisito) requisitos.getRowData();
        requisitoService.delete(requisito.getIdRequisito());
        requisito = new Requisito();
        return "ColetarRequisitos";
    }

    public String novoTermodeAbertura() {
        System.out.println("O projeto selecionado é" + projeto);
        projeto = service.findById(projeto.getIdProjeto());
        int idtermoabertura = 0;
        if ((projeto.getTermoabertura() != null)) {
            System.out.println("Entrou para pegar o termo de abertura existente");
            idtermoabertura = projeto.getTermoabertura().getIdTermoAbertura();
        }
        System.out.println("O termo de abertura tem código " + idtermoabertura);
        if (idtermoabertura > 0) {
            System.out.println("O termo de abertura do projeto selecionado é: " + projeto.getTermoabertura().getIdTermoAbertura());
            termodeabertura = (TermoAbertura) projeto.getTermoabertura();
            return "TermoAberturaDescricao";
        } else {
            System.out.println("Termo de Abertura não cadastrado, criando novo Termo de Abertura");
            termodeabertura = new TermoAbertura();
            termodeabertura.setProjeto(projeto);
            termoAberturaService.insert(termodeabertura);
            System.out.println("Novo termo de abertura criado, seu id é: " + termodeabertura.getIdTermoAbertura());
            projeto.setTermoabertura(termodeabertura);
            service.update(projeto);
            return "TermoAberturaDescricao";
        }

    }

    private Turma turmacombobox;

    public Turma getTurma() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        Usuario novousuario = usuarioService.findByEmail(emailuser);
        turma = novousuario.getTurma();
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String carregarturma() {
        projetosfiltrados = service.findbyturma(turma);
        return "ListagemProjetosInstrutor";
    }

    public DataModel getProjetosporaluno() {
        return projetosporaluno;
    }

    public void setProjetosporaluno(DataModel projetosporaluno) {
        this.projetosporaluno = projetosporaluno;
    }

    public UploadedFile getFileEAP() {
        return fileEAP;
    }

    public void setFileEAP(UploadedFile fileEAP) {
        this.fileEAP = fileEAP;
    }

    private StreamedContent image;

    public StreamedContent getImage() throws IOException {

        //if (this.projeto.getEAP() != null) {
        //sua regra para carregar os bytes   
        //System.out.println("EAP lenght" + this.projeto.getEAP().length);
        projeto = (Projeto) service.findById(projeto.getIdProjeto());
        InputStream is = null;
        if (this.projeto.getEAP() != null) {
            is = new ByteArrayInputStream(this.projeto.getEAP());
        } else {

        }

        return new DefaultStreamedContent(is, "", "" + this.projeto.getIdProjeto());
        //}
    }

    public void criarpdftermoabertura() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        List<TermoAbertura> termos = new ArrayList<>();
        termodeabertura = termoAberturaService.findByIdRelatorio(projeto.getTermoabertura().getIdTermoAbertura());
        termos.add(termodeabertura);

        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(termos, false);

        Map parametros = new HashMap();
        JasperPrint jpr = null;
        try {
            jpr = JasperFillManager.fillReport(context.getExternalContext().getRealPath("") + "/resources/TermoAbertura.jasper", parametros, jrs);

//            JasperExportManager.exportReportToPdfFile(jpr,
//                    context.getExternalContext().getRealPath("") + "/resources/TermoAbertura" + projeto.getNomeProjeto() + ".pdf");
//            System.out.println("Passou pela criação do PDF");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpr);
//            exporter.setParameters(JRExporterParameter.OUTPUT_STREAM, baos);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");//inline;attachment;
            response.setHeader("Content-disposition", "inline; filename=\"TermodeAbertura.pdf\"");
            //response.setContentLength(bytes.length);

            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jpr, servletOutputStream);
            //servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(ProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criarPlanoGerenciamentoProjeto() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();

        List<Projeto> projetoslist = new ArrayList<>();

        projeto = service.findByIdRelatorio(projeto.getIdProjeto());
        projetoslist.add(projeto);

        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(projetoslist, false);
        projeto = (Projeto) service.findById(projeto.getIdProjeto());

        InputStream is = null;
        if (this.projeto.getEAP() != null) {
            is = new ByteArrayInputStream(this.projeto.getEAP());
        } else {

        }

        Map parametros = new HashMap();
        System.out.println("O imputStream é " + is.toString());
        parametros.put("EAP", is);
        JasperPrint jpr = null;
        try {
            jpr = JasperFillManager.fillReport(context.getExternalContext().getRealPath("") + "/resources/PlanoGerenciamentoProjeto.jasper", parametros, jrs);

//            JasperExportManager.exportReportToPdfFile(jpr,
//                    context.getExternalContext().getRealPath("") + "/resources/TermoAbertura" + projeto.getNomeProjeto() + ".pdf");
//            System.out.println("Passou pela criação do PDF");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jpr);
//            exporter.setParameters(JRExporterParameter.OUTPUT_STREAM, baos);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");//inline;attachment;
            response.setHeader("Content-disposition", "inline; filename=\"PlanoGerenciamentoProjeto.pdf\"");
            //response.setContentLength(bytes.length);

            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jpr, servletOutputStream);
            //servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(ProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Projeto> getProjetosfiltrados() {
        return projetosfiltrados;
    }

    public void setProjetosfiltrados(List<Projeto> projetosfiltrados) {
        this.projetosfiltrados = projetosfiltrados;
    }

    public Turma getTurmacombobox() {
        return turmacombobox;
    }

    public void setTurmacombobox(Turma turmacombobox) {
        this.turmacombobox = turmacombobox;
    }

    public List<Turma> getTurmasdousuario() {
        TurmaService turmaService = new TurmaService();
        turmasdousuario = turmaService.findbyProfessor(usuariologado);
        return turmasdousuario;
    }

    public void setTurmasdousuario(List<Turma> turmasdousuario) {
        this.turmasdousuario = turmasdousuario;
    }

}
