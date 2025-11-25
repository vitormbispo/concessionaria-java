package trabalhopoo1.gui.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.dados.DadosVeiculos;
import trabalhopoo1.dados.DadosVendas;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.entidades.Venda;

/**
 * Menu de contexto para nós de objeto da árvore.
 */
public class MenuObjeto extends JPopupMenu{
    private final JMenuItem remover;
    private final JMenuItem editar;
    
    public MenuObjeto(Arvore arvore) {
        this.editar = new JMenuItem("Editar...");
        this.remover = new JMenuItem("Remover");
 
        remover.addActionListener(new RemoverObjetoHandler(arvore));
        editar.addActionListener(new EditarObjetoHandler(arvore));
        
        this.add(editar);
        this.add(remover);
    }
    
    /**
     * Handler para a opção "Remover" do menu
     */
    private class RemoverObjetoHandler implements ActionListener {
        private final Arvore arvore;
        
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
    
    /**
     * Handler para a opção "Editar..." do menu
     */
    private class EditarObjetoHandler implements ActionListener {
        private Arvore arvore;
        
        public EditarObjetoHandler(Arvore arvore) {
            this.arvore = arvore;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            NoObjeto no = (NoObjeto) arvore.getSelectionPath().getLastPathComponent();
            Object objeto = no.getUserObject();
            
            if(objeto instanceof Cliente cliente) {
                Main.getFormClientes().ativarEdicao(cliente);
                arvore.getViewPrincipal().mudarPainelCentral("FormularioClientes");
            }
            else if(objeto instanceof Funcionario funcionario) {
                Main.getFormFuncionarios().ativarEdicao(funcionario);
                arvore.getViewPrincipal().mudarPainelCentral("FormularioFuncionarios");
            }
            else if(objeto instanceof Veiculo veiculo) {
                Main.getFormVeiculos().ativarEdicao(veiculo);
                arvore.getViewPrincipal().mudarPainelCentral("FormularioVeiculos");
            }
            else if(objeto instanceof Venda)
                DadosVendas.remover((Venda) objeto);
        }
    }
}
