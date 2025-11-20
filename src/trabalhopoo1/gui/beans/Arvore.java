package trabalhopoo1.gui.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.views.ViewPrincipal;

public class Arvore extends JTree {
    private final DefaultTreeModel modelo;
    private final DefaultMutableTreeNode raiz;
    private final NoClasse<Cliente> noClientes;
    private final DefaultMutableTreeNode noFuncionarios;
    private final DefaultMutableTreeNode noVeiculos;
    private final DefaultMutableTreeNode noVendas;
    private final ViewPrincipal viewPrincipal;
    
    public Arvore(ViewPrincipal viewPrincipal) {
        super(new DefaultMutableTreeNode("Concessionária"));
        
        this.viewPrincipal = viewPrincipal;
        this.modelo = (DefaultTreeModel) this.getModel();
        this.raiz = (DefaultMutableTreeNode) this.modelo.getRoot();
        
        noClientes = new NoClasse<Cliente>("Clientes",modelo,raiz);
        noFuncionarios = new DefaultMutableTreeNode("Funcionários");
        noVeiculos = new DefaultMutableTreeNode("Veículos");
        noVendas = new DefaultMutableTreeNode("Vendas");
        
        raiz.add(noClientes);
        raiz.add(noFuncionarios);
        raiz.add(noVeiculos);
        raiz.add(noVendas);
        
        modelo.reload(raiz);
        configurarAcoes();
    }
    
    public final void configurarAcoes() {
        Arvore arvore = this;
        
        MenuObjeto menuObjeto = new MenuObjeto(arvore);
        MenuClasse menuClasse = new MenuClasse(arvore);
        
        this.add(menuClasse);
        this.add(menuObjeto);
        
        // Adiciona um MouseListener para detectar um clique com botão direito.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    int selecionado = arvore.getClosestRowForLocation(e.getX(), e.getY());
                    if(selecionado == 0)
                        return;
                    
                    arvore.setSelectionRow(selecionado);
                    Object objeto = arvore.getSelectionPath().getLastPathComponent();
                    
                    if(objeto instanceof NoClasse) {
                        menuClasse.show(e.getComponent(),e.getX(),e.getY());
                    } else if(objeto instanceof NoObjeto) {
                        menuObjeto.show(e.getComponent(),e.getX(),e.getY());
                    }
                }
            }
        });
    }
    
    public NoClasse<Cliente> getNoClientes() {
        return noClientes;
    }

    public DefaultMutableTreeNode getNoFuncionarios() {
        return noFuncionarios;
    }

    public DefaultMutableTreeNode getNoVeiculos() {
        return noVeiculos;
    }

    public DefaultMutableTreeNode getNoVendas() {
        return noVendas;
    }

    public ViewPrincipal getViewPrincipal() {
        return viewPrincipal;
    }
}
