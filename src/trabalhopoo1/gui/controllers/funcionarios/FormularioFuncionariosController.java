package trabalhopoo1.gui.controllers.funcionarios;

import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.excecoes.EntradaInvalidaException;

import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.views.funcionarios.FormularioFuncionarios;

public class FormularioFuncionariosController {
    private final ViewPrincipal viewPrincipal;
    private FormularioFuncionarios formulario;
    
    public FormularioFuncionariosController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Cadastra um novo funcionário
     * @param nome Nome do funcionario
     * @param numeroMatricula Numero de matrícula do funcionario
     * @param qualificacao Qualificação do funcionário
     * @param descFuncao Descrição da função do funcionário
     * @param cargaHoraria Carga horária do funcionário
     */
    public void cadastrarFuncionario(String nome, String numeroMatricula,String qualificacao, String descFuncao, String cargaHoraria) {
        if(!validarEntradas(nome,numeroMatricula,qualificacao,cargaHoraria,descFuncao))
            return;
        
        Funcionario funcionario = new Funcionario(nome,Long.parseLong(numeroMatricula),qualificacao,descFuncao,Integer.parseInt(cargaHoraria));
        
        DadosFuncionarios.cadastrar(funcionario);
        
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoFuncionarios().adicionarObjeto(funcionario);
        viewPrincipal.mudarPainelCentral("ConsultaFuncionarios");
        
    }
    
    /**
     * Modifica um funcionário
     * @param funcionario Funcionário a alterar
     * @param nome Nome do funcionario
     * @param matricula Numero de matrícula do funcionario
     * @param qualificacao Qualificação do funcionário
     * @param descFuncao Descrição da função do funcionário
     * @param cargaHoraria Carga horária do funcionário
     */
    public void modificarFuncionario(Funcionario funcionario,String nome, String matricula, String qualificacao, String descFuncao, String cargaHoraria) {
        boolean entradasValidas = true;
        
        if(!funcionario.getNome().equals(nome))
            entradasValidas = entradasValidas & validarNome(nome);
        if(!Long.toString(funcionario.getNumeroMatricula()).equals(matricula))
            entradasValidas = entradasValidas & validarMatricula(matricula);
        if(!funcionario.getQualificacao().equals(qualificacao))
            entradasValidas = entradasValidas & validarQualificacao(qualificacao);
        if(!Integer.toString(funcionario.getCargaHoraria()).equals(cargaHoraria))
            entradasValidas = entradasValidas & validarCargaHoraria(cargaHoraria);
        
        if(!entradasValidas)
            return;
        
        Funcionario novoFuncionario = new Funcionario(nome,Long.parseLong(matricula),qualificacao,descFuncao,Integer.parseInt(cargaHoraria));
        DadosFuncionarios.alterar(funcionario, nome, Long.parseLong(matricula), qualificacao, descFuncao, Integer.parseInt(cargaHoraria));
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoFuncionarios().alterarObjeto(funcionario, novoFuncionario);
        viewPrincipal.mudarPainelCentral("ConsultaFuncionarios");
    }
    
    // Validações
    
    public boolean validarNome(String nome) {
        boolean valido = true;
        
        try {
            DadosFuncionarios.validarNome(nome);
            formulario.getjLabelNomeVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelNomeVal().setText(e.getMessage());
            formulario.getjLabelNomeVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarMatricula(String matricula) {
        boolean valido = true;
        
        try {
            DadosFuncionarios.validarMatricula(matricula);
            formulario.getjLabelMatriculaVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelMatriculaVal().setText(e.getMessage());
            formulario.getjLabelMatriculaVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarQualificacao(String qualificacao) {
        boolean valido = true;
        
        try {
            DadosFuncionarios.validarQualificacao(qualificacao);
            formulario.getjLabelQualiVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelQualiVal().setText(e.getMessage());
            formulario.getjLabelQualiVal().setVisible(true);
        }
        return valido;
    }
    
       
    public boolean validarDescFuncao(String descFuncao) {
        boolean valido = true;
        
        try {
            DadosFuncionarios.validarDescFuncao(descFuncao);
            formulario.getjLabelDescVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelDescVal().setText(e.getMessage());
            formulario.getjLabelDescVal().setVisible(true);
        }
        return valido;
    }
       
    public boolean validarCargaHoraria(String carga) {
        boolean valido = true;
        
        try {
            DadosFuncionarios.validarCargaHoraria(carga);
            formulario.getjLabelCargaVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelCargaVal().setText(e.getMessage());
            formulario.getjLabelCargaVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarEntradas(String nome, String matricula, String qualificacao, String carga, String descFuncao) {
        return validarNome(nome) &
               validarMatricula(matricula) &
               validarQualificacao(qualificacao) &
               validarCargaHoraria(carga) &
               validarDescFuncao(descFuncao);
    }
    
    /**
     * Volta para a tela de consulta de funcionários
     */
    public void cancelar() {
        viewPrincipal.mudarPainelCentral("ConsultaFuncionarios");
    }
    
    public void setFormulario(FormularioFuncionarios formulario) {
        this.formulario = formulario;
    }
}
