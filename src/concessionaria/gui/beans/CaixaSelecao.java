package concessionaria.gui.beans;

import javax.swing.JCheckBox;

/**
 * Caixa para seleção de itens
 */
public class CaixaSelecao extends JCheckBox{
    private final int indice;
    public CaixaSelecao(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
}
