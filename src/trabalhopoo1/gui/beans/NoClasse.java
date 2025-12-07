package trabalhopoo1.gui.beans;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Nó da árvore representando uma classe de objetos.
 * @param <T> Classe
 */
public class NoClasse<T> extends DefaultMutableTreeNode{
    private final DefaultTreeModel modelo;
    private final DefaultMutableTreeNode raiz;
    
    public NoClasse(String nome, DefaultTreeModel modelo, DefaultMutableTreeNode raiz) {
        this.setUserObject(nome);
        this.modelo = modelo;
        this.raiz = raiz;
    }
    
    /**
     * Adiciona um objeto a esse nó como filho
     * @param objeto Objeto a adicionar
     */
    public void adicionarObjeto(T objeto) {
        NoObjeto<T> no = new NoObjeto(objeto,this);
        this.add(no);
        modelo.reload(raiz);
    }
    
    /**
     * Substitui um objeto filho
     * @param objeto
     * @param novoObjeto 
     */
    public void alterarObjeto(T objeto, T novoObjeto) {
        int indice = indiceObjeto(objeto);
        NoObjeto no = (NoObjeto) this.getChildAt(indice);
        no.setUserObject(novoObjeto);
        modelo.reload(raiz);
        }
    
    /**
     * Retorna o índice de determinado objeto na árvore, ou -1 caso objeto não esteja presente.
     * @param objeto Objeto a encontrar
     * @return Índice do objeto
     */
    public int indiceObjeto(T objeto) {
        for(int i = 0; i < this.getChildCount(); i++) {
            DefaultMutableTreeNode no = (DefaultMutableTreeNode)this.getChildAt(i);
            if(no.getUserObject().equals(objeto))
                return i;
        }
        return -1;
    }
    
    /**
     * Remove determinado objeto filho da árvore
     * @param objeto 
     */
    public void removerObjeto(T objeto) {
        int indice = indiceObjeto(objeto);
        
        if(indice == -1)
            return;
        
        this.remove(indice);
        modelo.reload();
    }
    
    /**
     * Remove todos os filhos desse nó
     */
    public void removerTodos() {
        this.removeAllChildren();
        modelo.reload();
    }
}
