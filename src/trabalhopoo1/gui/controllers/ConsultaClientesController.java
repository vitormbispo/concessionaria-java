package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.beans.BotaoIndexado;
import trabalhopoo1.gui.beans.ListaResultados;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Clientes
 */
public class ConsultaClientesController {
    
    private final ViewPrincipal viewPrincipal;
    private ListaResultados lista;
    private ArrayList<Cliente> clientesEncontrados;
    
    public ConsultaClientesController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Aciona a tela de cadastro de clientes.
     */
    public void adicionar() {
        viewPrincipal.mudarPainelCentral("FormularioClientes");
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
        clientesEncontrados = buscarClientes(chave,tipoBusca);
        
        if(clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        // Montando linhas para a lista de resultados
        clientesEncontrados.forEach((cliente) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                Long.toString(cliente.getId()),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getRg()
            ));
            linhas.add(linha);
        });
        
        // Colunas da lista
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("ID","Nome","Telefone","E-mail","CPF","RG","Editar","Remover"));
        
        lista = new ListaResultados(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                BotaoIndexado botao = (BotaoIndexado) e.getSource();
                int indice = botao.getIndice();
                
                Main.getFormClientes().ativarEdicao(clientesEncontrados.get(indice));
                Main.getConsClientes().reiniciar();
                
                viewPrincipal.mudarPainelCentral("FormularioClientes");
            }
        });
        
        painelResultados.add(lista);
        
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
    /**
     * Faz uma busca de clientes
     * @param chave Chave de busca
     * @param tipoBusca Tipo de busca
     * @return {@code ArrayList<Clientes>} com os clientes encontrados.
     */
    public ArrayList<Cliente> buscarClientes(String chave, String tipoBusca) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        switch(tipoBusca) {
            case "Todos" -> {
                DadosClientes.consultarTodos().forEach(clientes::add);
                break;
            }
            case "ID" -> {
                Cliente cliente = DadosClientes.consultarId(chave);
                if(cliente == null)
                    break;
                clientes.add(cliente);
                break;
            }
            case "Nome" -> {
                DadosClientes.consultarNome(chave).forEach(clientes::add);
                break;
            }
            case "CPF" -> {
                Cliente cliente = DadosClientes.consultarCpf(chave);
                
                if(cliente == null)
                    break;
                
                clientes.add(cliente);
                break;
            }
        }
        
        return clientes;
    }
    
    /**
     * Remove os clientes selecionados.
     * @param painelResultados painel de resultados
     */
    public void remover(JPanel painelResultados) {
        
        if(lista == null || lista.getSelecionados().isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum cliente selecionado para remover!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
                painelResultados, 
                "Deseja remover o(s) cliente(s) selecionado(s)?", "Remover cliente(s)", 
                JOptionPane.YES_NO_OPTION ,
                JOptionPane.QUESTION_MESSAGE);
        
        if(confirmacao == JOptionPane.NO_OPTION || confirmacao == JOptionPane.CANCEL_OPTION || confirmacao == JOptionPane.CLOSED_OPTION)
            return;
        
        lista.getSelecionados().forEach((indice) -> {
            Cliente cliente = clientesEncontrados.get(indice);
            DadosClientes.remover(cliente);
            
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