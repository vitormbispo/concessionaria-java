package trabalhopoo1.gui.controllers.funcionarios;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.gui.beans.ListaConsulta;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Funcionários
 */
public class ConsultaFuncionariosController {
    
    private ViewPrincipal viewPrincipal;
    private ListaConsulta lista;
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
        
        if(funcionariosEncontrados.size() == 0) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        funcionariosEncontrados.forEach((funcionario) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                funcionario.getNome(),
                Long.toString(funcionario.getNumeroMatricula()),
                funcionario.getQualificacao(),
                funcionario.getDescFuncao(),
                Integer.toString(funcionario.getCargaHoraria())
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("Nome","Nº Matrícula","Qualificação","Desc. Função","Carga Horária","Editar","Remover"));
        
        lista = new ListaConsulta(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                int indice = Integer.parseInt(botao.getName());
                
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
            case "Nome" -> {
                for(int i = 0; i < DadosFuncionarios.getFuncionarios().size(); i++) {
                    Funcionario funcionario = DadosFuncionarios.getFuncionarios().get(i);
                    
                    if(funcionario.getNome().contains(chave))
                        funcionarios.add(funcionario);
                }
                break;
            }
            case "Nº Matrícula" -> {
                for(int i = 0; i < DadosFuncionarios.getFuncionarios().size(); i++) {
                    Funcionario funcionario = DadosFuncionarios.getFuncionarios().get(i);
                    if(funcionario.getNumeroMatricula() == Long.parseLong(chave)) {
                        funcionarios.add(funcionario);
                        break;
                    }
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
                JOptionPane.QUESTION_MESSAGE);
        
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
