package trabalhopoo1.gui.beans;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabalhopoo1.gui.views.ViewPrincipal;

public class ListaConsulta extends JPanel{
    
    private ViewPrincipal viewPrincipal;
    private ArrayList<Integer> selecionados;
    
    public ListaConsulta(ArrayList<String> colunas, ArrayList<ArrayList<String>> linhas, ActionListener editarListener) {
        this.viewPrincipal = viewPrincipal;
        this.selecionados = new ArrayList<>();
        
        GridLayout layout = new GridLayout(0,colunas.size(),0,0);
        this.setLayout(layout);
        colunas.forEach((coluna) -> {
            JLabel atributo = new JLabel();
            atributo.setText(coluna);
            this.add(atributo);
        });
        
        for(int i = 0; i < linhas.size();i++) {
            ArrayList<String> linha = linhas.get(i);
            linha.forEach((campo) -> {
                JLabel dado = new JLabel();
                dado.setText(campo);
                this.add(dado);
            });
            
            JButton editar = new JButton("Editar");
            editar.setName(String.valueOf(i));
            editar.addActionListener(editarListener);
            this.add(editar);
            
            JCheckBox caixa = new JCheckBox();
            caixa.addActionListener(new CheckBoxHandler(i,caixa));
            this.add(caixa);
        }
    }

    public ArrayList<Integer> getSelecionados() {
        return selecionados;
    }
    
    private class CheckBoxHandler implements ActionListener{
        private int indice;
        private JCheckBox box;
        
        public CheckBoxHandler(int indice, JCheckBox box) {
            this.indice = indice;
            this.box = box;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(box.isSelected())
                selecionados.add(indice);
            else
                selecionados.remove(Integer.valueOf(indice));
        }
    }
}
