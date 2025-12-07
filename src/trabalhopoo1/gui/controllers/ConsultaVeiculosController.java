package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosVeiculos;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.gui.beans.BotaoIndexado;
import trabalhopoo1.gui.beans.ListaResultados;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Veículos
 */
public class ConsultaVeiculosController {
    
    private final ViewPrincipal viewPrincipal;
    private ListaResultados lista;
    private ArrayList<Veiculo> veiculosEncontrados;
    
    public ConsultaVeiculosController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Aciona a tela de cadastro de veículos.
     */
    public void adicionar() {
        viewPrincipal.mudarPainelCentral("FormularioVeiculos");
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
        veiculosEncontrados = buscarVeiculos(chave,tipoBusca);
        
        if(veiculosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        veiculosEncontrados.forEach((veiculo) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                Long.toString(veiculo.getId()),
                veiculo.getModelo(),
                veiculo.getMarca(),
                veiculo.getCor(),
                Integer.toString(veiculo.getAno()),
                Integer.toString(veiculo.getNumMarchas()),
                Integer.toString(veiculo.getNumPortas()),
                veiculo.getChassi()
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("ID","Modelo","Marca","Cor","Ano","Nº Marchas","Nº Portas","Chassi","Editar","Remover"));
        
        lista = new ListaResultados(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                BotaoIndexado botao = (BotaoIndexado) e.getSource();
                int indice = botao.getIndice();
                
                Main.getFormVeiculos().ativarEdicao(veiculosEncontrados.get(indice));
                Main.getConsVeiculos().reiniciar();
                
                viewPrincipal.mudarPainelCentral("FormularioVeiculos");
            }
        });
        
        painelResultados.add(lista);
        
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
    /**
     * Faz uma busca de veículos
     * @param chave Chave de busca
     * @param tipoBusca Tipo de busca
     * @return {@code ArrayList<Veiculo>} com os veículos encontrados.
     */
    public ArrayList<Veiculo> buscarVeiculos(String chave, String tipoBusca) {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        
        switch(tipoBusca) {
            case "Todos" -> {
                DadosVeiculos.consultarTodos().forEach(veiculos::add);
                break;
            }
            case "ID" -> {
                Veiculo veiculo = DadosVeiculos.consultarId(chave);
                if(veiculo == null)
                    break;
                veiculos.add(veiculo);
                break;
            }
            case "Modelo" -> {
                DadosVeiculos.consultarModelo(chave).forEach(veiculos::add);
                break;
            }
            case "Marca" -> {
                DadosVeiculos.consultarMarca(chave).forEach(veiculos::add);
                break;
            }
            case "Chassi" -> {
                Veiculo veiculo = DadosVeiculos.consultarChassi(chave);
                if(veiculo == null)
                    break;
                veiculos.add(veiculo);
                break;
            }
        }
        
        return veiculos;
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
            Veiculo veiculo = veiculosEncontrados.get(indice);
            viewPrincipal.getArvore().getNoVeiculos().removerObjeto(veiculo);
            DadosVeiculos.remover(veiculo);
            
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