package trabalhopoo1.gui.controllers.vendas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosVendas;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.gui.beans.ListaConsulta;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Vendas
 */
public class ConsultaVendasController {
    
    private ViewPrincipal viewPrincipal;
    private ListaConsulta lista;
    private ArrayList<Venda> vendasEncontrados;
    
    public ConsultaVendasController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Aciona a tela de cadastro de veículos.
     */
    public void adicionar() {
        Main.getFormVendasController().atualizarCaixas();
        viewPrincipal.mudarPainelCentral("FormularioVendas");
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
        vendasEncontrados = buscarVendas(chave,tipoBusca);
        
        if(vendasEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        vendasEncontrados.forEach((venda) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                venda.getCliente().getNome(),
                venda.getFuncionario().getNome(),
                venda.getVeiculo().toString(),
                venda.getData().toString(),
                Double.toString(venda.getValor())
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("Cliente","Funcionário","Veículo","Data","Valor","Editar","Remover"));
        
        lista = new ListaConsulta(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                int indice = Integer.parseInt(botao.getName());
                
                Main.getFormVendas().ativarEdicao(vendasEncontrados.get(indice));
                Main.getFormVendasController().atualizarCaixas();
                Main.getConsVendas().reiniciar();
                
                viewPrincipal.mudarPainelCentral("FormularioVendas");
            }
        });
        
        painelResultados.add(lista);
        
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
    /**
     * Faz uma busca de venda
     * @param chave Chave de busca
     * @param tipoBusca Tipo de busca
     * @return {@code ArrayList<Venda>} com as vendas encontrados.
     */
    public ArrayList<Venda> buscarVendas(String chave, String tipoBusca) {
        ArrayList<Venda> vendas = new ArrayList<>();
        
        switch(tipoBusca) {
            case "Cliente" -> {
                for(int i = 0; i < DadosVendas.getVendas().size(); i++) {
                    Venda venda = DadosVendas.getVendas().get(i);
                    
                    if(venda.getCliente().getCpf().contains(chave))
                        vendas.add(venda);
                }
                break;
            }
            case "Funcionário" -> {
                for(int i = 0; i < DadosVendas.getVendas().size(); i++) {
                    Venda venda = DadosVendas.getVendas().get(i);
                    if(Long.toString(venda.getFuncionario().getNumeroMatricula()).equals(chave)) {
                        vendas.add(venda);
                        break;
                    }
                }
                break;
            }
        }
        
        return vendas;
    }
    
    /**
     * Remove os veículos selecionados.
     * @param painelResultados painel de resultados
     */
    public void remover(JPanel painelResultados) {
        
        if(lista == null || lista.getSelecionados().isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum veículo selecionado para remover!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
                painelResultados, 
                "Deseja remover o(s) veículo(s) selecionado(s)?", "Remover veículo(s)", 
                JOptionPane.YES_NO_OPTION ,
                JOptionPane.WARNING_MESSAGE);
        
        if(confirmacao == JOptionPane.NO_OPTION || confirmacao == JOptionPane.CANCEL_OPTION || confirmacao == JOptionPane.CLOSED_OPTION)
            return;
        
        lista.getSelecionados().forEach((indice) -> {
            Venda venda = vendasEncontrados.get(indice);
            viewPrincipal.getArvore().getNoVendas().removerObjeto(venda);
            DadosVendas.remover(venda);
            
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
