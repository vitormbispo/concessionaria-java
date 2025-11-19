package trabalhopoo1.gui.controllers;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.beans.ListaConsulta;
import trabalhopoo1.gui.beans.ListaRemocao;
import trabalhopoo1.gui.views.ViewPrincipal;

public class RemocaoClientesController {
    
    private ViewPrincipal viewPrincipal;
    private ListaRemocao lista;
    private ArrayList<Cliente> clientesEncontrados;
    
    public RemocaoClientesController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        this.clientesEncontrados = new ArrayList<>();
    }
    
    public void buscar(String chave, String tipo, JPanel painelResultados) {
        painelResultados.setLayout(new GridLayout());
        painelResultados.removeAll();
        
        ArrayList<ArrayList<String>> linhas = new ArrayList<>();
        clientesEncontrados = new ArrayList<>();
        
        switch(tipo) {
            case "Nome" -> {
                for(int i = 0; i < DadosClientes.getClientes().size(); i++) {
                    Cliente cliente = DadosClientes.getClientes().get(i);
                    
                    if(cliente.getNome().contains(chave))
                        clientesEncontrados.add(cliente);
                }
                break;
            }
            case "CPF" -> {
                for(int i = 0; i < DadosClientes.getClientes().size(); i++) {
                    Cliente cliente = DadosClientes.getClientes().get(i);
                    if(cliente.getCpf().equals(chave)) {
                        clientesEncontrados.add(cliente);
                        break;
                    }
                }
                break;
            }
        }
        
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
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("Nome","Telefone","E-mail","CPF","RG","Selecionado"));
        lista = new ListaRemocao(colunas,linhas);
        
        painelResultados.add(lista);
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
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
            viewPrincipal.getArvore().removerCliente(cliente);
            DadosClientes.remover(cliente);
            
        });
        painelResultados.setVisible(false);
    }
    
    public void voltar() {
        viewPrincipal.mudarPainelCentral("MenuPrincipal");
    }
}
