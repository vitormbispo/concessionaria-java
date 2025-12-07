package trabalhopoo1.gui.beans;

import javax.swing.JButton;

/**
 * Botão que armazena um índice
 */
public class BotaoIndexado extends JButton{
    private final int indice;
    
    public BotaoIndexado(String nome, int indice) {
        super(nome);
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}
