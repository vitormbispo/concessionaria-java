package trabalhopoo1.gui.views;

import trabalhopoo1.gui.beans.Arvore;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import trabalhopoo1.dados.BancoDados;

/**
 * Janela princiapl do programa
 */
public class ViewPrincipal extends JFrame{
    private final JPanel painelPrincipal;
    private final JPanel painelCentro;
    private final JPanel painelArvore;
    private final CardLayout layoutCentral;
    
    private final Arvore arvore;
    
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
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                BancoDados.encerrar();
                System.exit(0);
            }
        });
        
        this.getContentPane().add(painelPrincipal);
        this.setVisible(true);
    }
    
    /**
     * Adiciona um novo painel ao layout do painel central.
     * @param painel Painel a adicionar
     * @param nome Nome do painel
     */
    public void adicionarPainelCentral(JPanel painel, String nome) {
        this.painelCentro.add(painel,nome);
        this.painelCentro.revalidate();
        this.painelCentro.repaint();
    }
    
    /**
     * Altera o painel central
     * @param nome Nome da painel
     */
    public void mudarPainelCentral(String nome) {
        this.layoutCentral.show(painelCentro, nome);
    }

    public Arvore getArvore() {
        return this.arvore;
    } 
}
