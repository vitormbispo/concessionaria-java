package trabalhopoo1.gui.beans;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.gui.views.ViewPrincipal;

/**
 * Arvore que exibe registros de clientes, funcionários, veículos e vendas da concessionária. <br>
 * Suporta menus de contexto com o botão direito do mouse.
 * @author vitorbispo
 */
public class Arvore extends JTree {
    private final DefaultTreeModel modelo;
    private final DefaultMutableTreeNode raiz;
    private final NoClasse<Cliente> noClientes;
    private final NoClasse<Funcionario> noFuncionarios;
    private final NoClasse<Veiculo> noVeiculos;
    private final NoClasse<Venda> noVendas;
    private final ViewPrincipal viewPrincipal;
    
    public Arvore(ViewPrincipal viewPrincipal) {
        super(new DefaultMutableTreeNode("Concessionária"));
        
        this.viewPrincipal = viewPrincipal;
        this.modelo = (DefaultTreeModel) this.getModel();
        this.raiz = (DefaultMutableTreeNode) this.modelo.getRoot();
        
        noClientes = new NoClasse<>("Clientes",modelo,raiz);
        noFuncionarios = new NoClasse<>("Funcionários",modelo,raiz);
        noVeiculos = new NoClasse<>("Veículos",modelo,raiz);
        noVendas = new NoClasse<>("Vendas",modelo,raiz);
        
        raiz.add(noClientes);
        raiz.add(noFuncionarios);
        raiz.add(noVeiculos);
        raiz.add(noVendas);
        
        modelo.reload(raiz);
        configurarAcoes();
    }
    
    /**
     * Configura os eventos do mouse e menus de contexto.
     */
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
                    int selecionado = arvore.getClosestRowForLocation(e.getX(), e.getY()); // Seleciona a linha mais próxima do mouse
                    if(selecionado == 0) // Ignora raíz
                        return;
                    
                    arvore.setSelectionRow(selecionado); 
                    Object objeto = arvore.getSelectionPath().getLastPathComponent();
                    
                    // Mostra menus diferentes se o nó selecioado for de um objeto ou classe.
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

    public NoClasse<Funcionario> getNoFuncionarios() {
        return noFuncionarios;
    }

    public NoClasse<Veiculo> getNoVeiculos() {
        return noVeiculos;
    }

    public NoClasse<Venda> getNoVendas() {
        return noVendas;
    }

    public ViewPrincipal getViewPrincipal() {
        return viewPrincipal;
    }
}
