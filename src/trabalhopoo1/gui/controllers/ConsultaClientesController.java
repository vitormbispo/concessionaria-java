package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.beans.ListaConsulta;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Controlador da tela de Consulta de Clientes
 */
public class ConsultaClientesController {
    
    private ViewPrincipal viewPrincipal;
    private ListaConsulta lista;
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
        
        if(clientesEncontrados.size() == 0) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        clientesEncontrados.forEach((cliente) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getRg()
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("Nome","Telefone","E-mail","CPF","RG","Editar","Remover"));
        
        lista = new ListaConsulta(colunas,linhas, new ActionListener(){
            // Handler do botão editar
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                int indice = Integer.parseInt(botao.getName());
                System.out.println("Indice: "+indice);
                
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
            case "Nome" -> {
                for(int i = 0; i < DadosClientes.getClientes().size(); i++) {
                    Cliente cliente = DadosClientes.getClientes().get(i);
                    
                    if(cliente.getNome().contains(chave))
                        clientes.add(cliente);
                }
                break;
            }
            case "CPF" -> {
                for(int i = 0; i < DadosClientes.getClientes().size(); i++) {
                    Cliente cliente = DadosClientes.getClientes().get(i);
                    if(cliente.getCpf().equals(chave)) {
                        clientes.add(cliente);
                        break;
                    }
                }
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
        
        if(lista.getSelecionados().isEmpty()) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum cliente selecionado para remover!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(
                painelResultados, 
                "Deseja remover o(s) cliente(s) selecionado(s)?", "Remover cliente(s)", 
                JOptionPane.YES_NO_OPTION ,
                JOptionPane.QUESTION_MESSAGE);
        
        if(confirmacao == JOptionPane.NO_OPTION)
            return;
        
        lista.getSelecionados().forEach((indice) -> {
            Cliente cliente = clientesEncontrados.get(indice);
            viewPrincipal.getArvore().getNoClientes().removerObjeto(cliente);
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
