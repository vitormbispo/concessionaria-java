package trabalhopoo1.gui.beans;

import javax.swing.tree.DefaultMutableTreeNode;

public class NoObjeto<T> extends DefaultMutableTreeNode{
    private final NoClasse<T> classe;
    
    public NoObjeto(T objeto,NoClasse<T> classe) {
        this.setUserObject(objeto);
        this.classe = classe;
    }

    public NoClasse<T> getClasse() {
        return classe;
    }
}
