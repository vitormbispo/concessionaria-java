package trabalhopoo1.gui.beans;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListaRemocao extends JPanel{
    
    private ArrayList<Integer> selecionados;
    
    public ListaRemocao(ArrayList<String> colunas, ArrayList<ArrayList<String>> linhas) {
        selecionados = new ArrayList();
        
        GridLayout layout = new GridLayout(0,colunas.size(),0,0);
        this.setLayout(layout);
        colunas.forEach((coluna) -> {
            JLabel atributo = new JLabel();
            atributo.setText(coluna);
            this.add(atributo);
        });
        
        for(int i = 0; i < linhas.size(); i++) {
            ArrayList<String> linha = linhas.get(i);
            
            linha.forEach((campo) -> {
                JLabel dado = new JLabel();
                dado.setText(campo);
                this.add(dado);
            });
            JCheckBox box = new JCheckBox();
            box.addActionListener(new ListenerCaixa(i,box));
            this.add(box);
        }
    }
    
    public ArrayList<Integer> getSelecionados() {
        return selecionados;
    }
    
    private class ListenerCaixa implements ActionListener{
        private int indice;
        private JCheckBox box;
        
        public ListenerCaixa(int indice, JCheckBox box) {
            this.indice = indice;
            this.box = box;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(box.isSelected())
                selecionados.add(indice);
            else
                selecionados.remove(Integer.valueOf(indice));
            System.out.println("Selecionados: ");
            System.out.println(selecionados);
        }
    }

    
    
}


