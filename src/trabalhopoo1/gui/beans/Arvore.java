package trabalhopoo1.gui.beans;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import trabalhopoo1.entidades.Cliente;

public class Arvore extends JTree {
    private DefaultTreeModel modelo;
    private DefaultMutableTreeNode raiz;
    private DefaultMutableTreeNode noClientes;
    private DefaultMutableTreeNode noFuncionarios;
    private DefaultMutableTreeNode noVeiculos;
    private DefaultMutableTreeNode noVendas;
    
    public Arvore() {
        super(new DefaultMutableTreeNode("Concessionária"));
        this.modelo = (DefaultTreeModel) this.getModel();
        this.raiz = (DefaultMutableTreeNode) this.modelo.getRoot();
        
        noClientes = new DefaultMutableTreeNode("Clientes");
        noFuncionarios = new DefaultMutableTreeNode("Funcionários");
        noVeiculos = new DefaultMutableTreeNode("Veículos");
        noVendas = new DefaultMutableTreeNode("Vendas");
        
        raiz.add(noClientes);
        raiz.add(noFuncionarios);
        raiz.add(noVeiculos);
        raiz.add(noVendas);
        
        modelo.reload(raiz);
    }
    
    public void adicionarCliente(Cliente cliente) {
        DefaultMutableTreeNode no = new DefaultMutableTreeNode(cliente);
        no.setUserObject(cliente);
        noClientes.add(no);
        modelo.reload(raiz);
    }
    
    public int indiceCliente(Cliente cliente) {
        for(int i = 0; i < noClientes.getChildCount(); i++) {
            DefaultMutableTreeNode no = (DefaultMutableTreeNode)noClientes.getChildAt(i);
            if(no.getUserObject().equals(cliente))
                return i;
        }
        return -1;
    }
    
    public void removerCliente(Cliente cliente) {
        int indice = indiceCliente(cliente);
        
        if(indice == -1)
            return;
        
        noClientes.remove(indice);
        modelo.reload();
    }
}
