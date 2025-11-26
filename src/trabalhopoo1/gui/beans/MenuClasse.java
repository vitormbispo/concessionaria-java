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

/**
 * Menu de contexto para nós de classe da árvore.
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
    
    /**
     * Handler para a opção "Remover todos" do menu
     */
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

            if(confirmacao == JOptionPane.YES_OPTION) {
                NoClasse no = (NoClasse) arvore.getSelectionPath().getLastPathComponent();
                no.removerTodos();
                
                Object objeto = no.getUserObject();
            
                if(objeto.equals("Clientes"))
                    DadosClientes.removerTodos();
                if(objeto.equals("Funcionários"))
                    DadosFuncionarios.removerTodos();
                if(objeto.equals("Veículos"))
                    DadosVeiculos.removerTodos();
                if(objeto.equals("Vendas"))
                    DadosVendas.removerTodos();
                
                if(Main.getTelaAtual().equals("FormularioVendas")) {
                    Main.getFormVendasController().atualizarCaixas();
                }
            }
        }
    }
    
    /**
     * Handler para a opção "Adicionar..." do menu
     */
    private class AdicionarObjetoHandler implements ActionListener{
        private final Arvore arvore;
        
        public AdicionarObjetoHandler(Arvore arvore) {
            this.arvore = arvore;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            NoClasse no = (NoClasse) arvore.getSelectionPath().getLastPathComponent();
            Object objeto = no.getUserObject();
            
            if(objeto.equals("Clientes"))
                arvore.getViewPrincipal().mudarPainelCentral("FormularioClientes");
            if(objeto.equals("Funcionários"))
                arvore.getViewPrincipal().mudarPainelCentral("FormularioFuncionarios");
            if(objeto.equals("Veículos"))
                arvore.getViewPrincipal().mudarPainelCentral("FormularioVeiculos");
            if(objeto.equals("Vendas")) {
                Main.getFormVendasController().atualizarCaixas();
                arvore.getViewPrincipal().mudarPainelCentral("FormularioVendas");
            }
                
        }
    }
}
