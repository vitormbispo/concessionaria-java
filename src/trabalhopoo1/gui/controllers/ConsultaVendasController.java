package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosVendas;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.gui.beans.BotaoIndexado;
import trabalhopoo1.gui.beans.ListaResultados;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Vendas
 */
public class ConsultaVendasController {
    
    private final ViewPrincipal viewPrincipal;
    private ListaResultados lista;
    private ArrayList<Venda> vendasEncontrados;
    
    public ConsultaVendasController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Aciona a tela de cadastro de veículos.
     */
    public void adicionar() {
        Main.getFormVendasController().atualizarListas();
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
              Long.toString(venda.getId()),
                venda.getCliente() != null ? venda.getCliente().getNome() : "*Cliente excluído*",
                venda.getFuncionario() != null ? venda.getFuncionario().getNome() : "*Funcionário excluído*",
                venda.getVeiculo() != null ? venda.getVeiculo().toString() : "*Veículo excluído*",
                venda.getData().toString(),
                Double.toString(venda.getValor())
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("ID","Cliente","Funcionário","Veículo","Data","Valor","Editar","Remover"));
        
        lista = new ListaResultados(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                BotaoIndexado botao = (BotaoIndexado) e.getSource();
                int indice = botao.getIndice();
                
                Main.getFormVendas().ativarEdicao(vendasEncontrados.get(indice));
                Main.getFormVendasController().atualizarListas();
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
            case "Todas" -> {
                DadosVendas.consultarTodas().forEach(vendas::add);
                break;
            }
            case "ID" -> {
                Venda venda = DadosVendas.consultarId(chave);
                if(venda == null)
                    break;
                vendas.add(venda);
                break;
            }
            case "Cliente (CPF)" -> {
                DadosVendas.consultarCPF(chave).forEach(vendas::add);
                break;
            }
            case "Funcionário (Nº)" -> {
                DadosVendas.consultarNMatricula(chave).forEach(vendas::add);
                break;
            }
            case "Veículo (Chassi)" -> {
                DadosVendas.consultarChassi(chave).forEach(vendas::add);
                break;
            }
            case "Nome do cliente" -> {
                DadosVendas.consultarNomeCliente(chave).forEach(vendas::add);
                break;
            }
            case "Nome do funcionário" -> {
                DadosVendas.consultarNomeFuncionario(chave).forEach(vendas::add);
                break;
            }
            case "Modelo do veículo" -> {
                DadosVendas.consultarModeloVeiculo(chave).forEach(vendas::add);
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