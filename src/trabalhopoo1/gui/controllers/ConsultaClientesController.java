package trabalhopoo1.gui.controllers;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.beans.ListaConsulta;
import trabalhopoo1.gui.views.ViewPrincipal;

public class ConsultaClientesController {
    
    private ViewPrincipal viewPrincipal;
    public ConsultaClientesController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    public void buscar(String chave, String tipo, JPanel painelResultados) {
        painelResultados.setLayout(new GridLayout());
        painelResultados.removeAll();
        
        ArrayList<ArrayList<String>> linhas = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        switch(tipo) {
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
        
        if(clientes.size() == 0) {
            JOptionPane.showMessageDialog(painelResultados, "Nenhum dado encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        clientes.forEach((cliente) -> {
            ArrayList linha = new ArrayList(Arrays.asList(
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getRg()
            ));
            linhas.add(linha);
        });
        
        ArrayList<String> colunas = new ArrayList<>(Arrays.asList("Nome","Telefone","E-mail","CPF","RG"));
        painelResultados.add(new ListaConsulta(colunas,linhas));
        painelResultados.revalidate();
        painelResultados.repaint();
        painelResultados.setVisible(true);
    }
    
    public void voltar() {
        viewPrincipal.mudarPainelCentral("MenuPrincipal");
    }
}
