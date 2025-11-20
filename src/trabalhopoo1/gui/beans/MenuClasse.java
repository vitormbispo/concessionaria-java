/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopoo1.gui.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import trabalhopoo1.entidades.Cliente;

/**
 *
 * @author vitorbispo
 */
public class MenuClasse extends JPopupMenu{
    private final JMenuItem adicionar;
    private final JMenuItem remover;
    
    public MenuClasse(Arvore arvore) {
        this.adicionar = new JMenuItem("Adicionar...");
        this.remover = new JMenuItem("Remover Todos");
        
        
        remover.addActionListener(new RemoverTodosHandler(arvore));
        adicionar.addActionListener(new AdicionarObjetoHandler(arvore));
        
        this.add(adicionar);
        this.add(remover);
        
    }
    
    private class RemoverTodosHandler implements ActionListener {
        private Arvore arvore;
        
        public RemoverTodosHandler(Arvore arvore) {
            this.arvore = arvore;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    null, 
                    "Deseja excluir todos os registros?\n Essa ação não pode ser desfeita.", 
                    "Remover todos", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if(confirmacao == JOptionPane.NO_OPTION)
                return;

            NoClasse no = (NoClasse) arvore.getSelectionPath().getLastPathComponent();
            no.removerTodos();
        }
    }
    
    private class AdicionarObjetoHandler implements ActionListener{
        private Arvore arvore;
        
        public AdicionarObjetoHandler(Arvore arvore) {
            this.arvore = arvore;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            NoClasse no = (NoClasse) arvore.getSelectionPath().getLastPathComponent();
            Object objeto = no.getUserObject();
            
            if(objeto.equals("Clientes"))
                arvore.getViewPrincipal().mudarPainelCentral("FormularioClientes");
        }
    }
}
