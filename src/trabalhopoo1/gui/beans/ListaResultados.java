package trabalhopoo1.gui.beans;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static trabalhopoo1.util.Util.abreviar;

/**
 * Exibe uma lista de resultados em formato de tabela.
 */
public class ListaResultados extends JPanel{
    
    private final ArrayList<Integer> selecionados;
    
    public ListaResultados(ArrayList<String> colunas, ArrayList<ArrayList<String>> linhas, ActionListener editarListener) {
        this.selecionados = new ArrayList<>();
        
        GridLayout layout = new GridLayout(0,colunas.size(),5,0);
        this.setMaximumSize(new Dimension(720,10000));
        this.setLayout(layout);
        
        // Constrói as colunas
        colunas.forEach((coluna) -> {
            JLabel atributo = new JLabel();
            atributo.setText(coluna);
            this.add(atributo);
        });
        
        // Constrói as linhas, com botões de edição e caixas de seleção para remoção.
        for(int i = 0; i < linhas.size();i++) {
            ArrayList<String> linha = linhas.get(i);
            linha.forEach((campo) -> {
                JLabel dado = new JLabel();
                dado.setText(abreviar(campo,20));
                dado.setToolTipText(campo);
                this.add(dado);
            });
            
            BotaoIndexado editar = new BotaoIndexado("Editar",i);
            editar.addActionListener(editarListener);
            this.add(editar);
            
            CaixaSelecao caixa = new CaixaSelecao(i);
            caixa.addActionListener(new CheckBoxHandler(i,caixa));
            this.add(caixa);
        }
    }

    public ArrayList<Integer> getSelecionados() {
        return selecionados;
    }
    
    /**
     * Handler para as caixas de seleção
     */
    private class CheckBoxHandler implements ActionListener{
        private final CaixaSelecao caixa;
        
        public CheckBoxHandler(int indice, CaixaSelecao caixa) {
            this.caixa = caixa;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(caixa.isSelected())
                selecionados.add(caixa.getIndice());
            else
                selecionados.remove(Integer.valueOf(caixa.getIndice()));
        }
    }
}
