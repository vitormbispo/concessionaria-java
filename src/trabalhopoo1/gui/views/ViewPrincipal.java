package trabalhopoo1.gui.views;

import trabalhopoo1.gui.beans.Arvore;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ViewPrincipal extends JFrame{
    private JPanel painelPrincipal;
    private JPanel painelCentro;
    private JPanel painelArvore;
    private CardLayout layoutCentral;
    
    private Arvore arvore;
    
    public ViewPrincipal() {
        this.setTitle("Concessionária");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        
        painelArvore = new JPanel();
        painelArvore.setLayout(new GridLayout());
        arvore = new Arvore(this);
        painelArvore.add(arvore);
        
        painelCentro = new JPanel(new CardLayout());
        layoutCentral = (CardLayout) painelCentro.getLayout();
        
        painelPrincipal.add(painelCentro);
        painelPrincipal.add(painelArvore,BorderLayout.WEST);
        
        this.getContentPane().add(painelPrincipal);
        this.setVisible(true);
    }
    
    public void adicionarPainelCentral(JPanel painel, String nome) {
        this.painelCentro.add(painel,nome);
        this.painelCentro.revalidate();
        this.painelCentro.repaint();
    }
    public void mudarPainelCentral(String nome) {
        this.layoutCentral.show(painelCentro, nome);
    }
    
    public Arvore getArvore() {
        return this.arvore;
    } 
}
