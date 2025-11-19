package trabalhopoo1.gui.beans;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListaConsulta extends JPanel{
    
    public ListaConsulta(ArrayList<String> colunas, ArrayList<ArrayList<String>> linhas) {
        GridLayout layout = new GridLayout(0,colunas.size(),0,0);
        this.setLayout(layout);
        colunas.forEach((coluna) -> {
            JLabel atributo = new JLabel();
            atributo.setText(coluna);
            this.add(atributo);
        });
        
        linhas.forEach((linha) -> {
            linha.forEach((campo) -> {
                JLabel dado = new JLabel();
                dado.setText(campo);
                this.add(dado);
            });
        });
    }
}
