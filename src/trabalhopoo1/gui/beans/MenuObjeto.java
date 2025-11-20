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
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.dados.DadosVeiculos;
import trabalhopoo1.dados.DadosVendas;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.entidades.Venda;

/**
 *
 * @author vitorbispo
 */
public class MenuObjeto extends JPopupMenu{
    private final JMenuItem remover;
    
    public MenuObjeto(Arvore arvore) {
        this.remover = new JMenuItem("Remover");
        
        remover.addActionListener(new RemoverObjetoHandler(arvore));
        this.add(remover);
    }
    
    private class RemoverObjetoHandler implements ActionListener {
        private Arvore arvore;
        
        public RemoverObjetoHandler(Arvore arvore) {
            this.arvore = arvore;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    null, 
                    "Deseja excluir esse objeto?\n Essa ação não pode ser desfeita.", 
                    "Remover", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if(confirmacao == JOptionPane.NO_OPTION)
                return;

            NoObjeto no = (NoObjeto) arvore.getSelectionPath().getLastPathComponent();
            Object objeto = no.getUserObject();
            no.getClasse().removerObjeto(objeto);
            
            if(objeto instanceof Cliente)
                DadosClientes.remover((Cliente) objeto);
            else if(objeto instanceof Funcionario)
                DadosFuncionarios.remover((Funcionario) objeto);
            else if(objeto instanceof Veiculo)
                DadosVeiculos.remover((Veiculo) objeto);
            else if(objeto instanceof Venda)
                DadosVendas.remover((Venda) objeto);
        }
    }
}
