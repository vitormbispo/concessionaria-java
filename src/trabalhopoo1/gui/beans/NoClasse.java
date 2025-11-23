package trabalhopoo1.gui.beans;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class NoClasse<T> extends DefaultMutableTreeNode{
    private DefaultTreeModel modelo;
    private DefaultMutableTreeNode raiz;
    
    public NoClasse(String nome, DefaultTreeModel modelo, DefaultMutableTreeNode raiz) {
        this.setUserObject(nome);
        this.modelo = modelo;
        this.raiz = raiz;
    }
    
    public void adicionarObjeto(T objeto) {
        NoObjeto no = new NoObjeto<T>(objeto,this);
        this.add(no);
        modelo.reload(raiz);
    }
    
    public void alterarObjeto(T objeto, T novoObjeto) {
        int indice = indiceObjeto(objeto);
        NoObjeto no = (NoObjeto) this.getChildAt(indice);
        no.setUserObject(novoObjeto);
        modelo.reload(raiz);
    }
    
    public int indiceObjeto(T objeto) {
        for(int i = 0; i < this.getChildCount(); i++) {
            DefaultMutableTreeNode no = (DefaultMutableTreeNode)this.getChildAt(i);
            if(no.getUserObject().equals(objeto))
                return i;
        }
        return -1;
    }
    
    public void removerObjeto(T objeto) {
        int indice = indiceObjeto(objeto);
        
        if(indice == -1)
            return;
        
        this.remove(indice);
        modelo.reload();
    }
    
    public void removerTodos() {
        this.removeAllChildren();
        modelo.reload();
    }
}
