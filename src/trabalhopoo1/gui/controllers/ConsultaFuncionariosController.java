package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.gui.beans.BotaoIndexado;
import trabalhopoo1.gui.beans.ListaResultados;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Funcionários
 */
public class ConsultaFuncionariosController {
    
    private final ViewPrincipal viewPrincipal;
    private ListaResultados lista;
    private ArrayList<Funcionario> funcionariosEncontrados;
    
    public ConsultaFuncionariosController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Aciona a tela de cadastro de funcionários.
     */
    public void adicionar() {
        viewPrincipal.mudarPainelCentral("FormularioFuncionarios");
    }
    
    /**
     * Exibe os resultados de uma busca no painel de resultados.
     * @param chave Chave da busca
     * @param tipoBusca Tipo de busca
     * @param painelResultados Painel de resultados
     */
    public void exibirResultado(String chave, String tipoBusca, JPanel painelResultados) {
        painelResultados.setLayout(new GridLayout());
        painelResultados.removeAll();
        
        ArrayList<ArrayList<String>> linhas = new ArrayList<>();
        funcionariosEncontrados = buscarFuncionarios(chave,tipoBusca);
        
        if(funcionariosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        funcionariosEncontrados.forEach((funcionario) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                Long.toString(funcionario.getId()),
                funcionario.getNome(),
                Long.toString(funcionario.getNumeroMatricula()),
                funcionario.getQualificacao(),
                funcionario.getDescFuncao(),
                Integer.toString(funcionario.getCargaHoraria())
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("ID","Nome","Nº Matrícula","Qualificação","Desc. Função","Carga Horária","Editar","Remover"));
        
        lista = new ListaResultados(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                BotaoIndexado botao = (BotaoIndexado) e.getSource();
                int indice = botao.getIndice();
                
                Main.getFormFuncionarios().ativarEdicao(funcionariosEncontrados.get(indice));
                Main.getConsFuncionarios().reiniciar();
                
                viewPrincipal.mudarPainelCentral("FormularioFuncionarios");
            }
        });
        
        painelResultados.add(lista);
        
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
    /**
     * Faz uma busca de funcionários
     * @param chave Chave de busca
     * @param tipoBusca Tipo de busca
     * @return {@code ArrayList<Funcionario>} com os funcionários encontrados.
     */
    public ArrayList<Funcionario> buscarFuncionarios(String chave, String tipoBusca) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        switch(tipoBusca) {
            case "ID" -> {
                Funcionario funcionario = DadosFuncionarios.consultarId(chave);
                if(funcionario == null)
                    break;
                funcionarios.add(funcionario);
                break;
            }
            case "Nome" -> {
                DadosFuncionarios.consultarNome(chave).forEach(funcionarios::add);
                break;
            }
            case "Nº Matrícula" -> {
                try {
                    Funcionario funcionario = DadosFuncionarios.consultarMatricula(Long.parseLong(chave));
                    if(funcionario == null)
                        break;
                    funcionarios.add(funcionario);
                }
                catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "O Nº de matrícula deve ser válido!!", "Erro", JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
        }
        
        return funcionarios;
    }
    
    /**
     * Remove os funcionários selecionados.
     * @param painelResultados painel de resultados
     */
    public void remover(JPanel painelResultados) {
        
        if(lista == null || lista.getSelecionados().isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum funcionário selecionado para remover!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
                painelResultados, 
                "Deseja remover o(s) funcionário(s) selecionado(s)?", "Remover funcionário(s)", 
                JOptionPane.YES_NO_OPTION ,
                JOptionPane.WARNING_MESSAGE);
        
        if(confirmacao == JOptionPane.NO_OPTION || confirmacao == JOptionPane.CANCEL_OPTION || confirmacao == JOptionPane.CLOSED_OPTION)
            return;
        
        lista.getSelecionados().forEach((indice) -> {
            Funcionario funcionario = funcionariosEncontrados.get(indice);
            viewPrincipal.getArvore().getNoFuncionarios().removerObjeto(funcionario);
            DadosFuncionarios.remover(funcionario);
            
        });
        painelResultados.setVisible(false);
    }
    
    /**
     * Retorna para o menu principal
     */
    public void voltar() {
        viewPrincipal.mudarPainelCentral("MenuPrincipal");
    }
}