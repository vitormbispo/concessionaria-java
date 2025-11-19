package trabalhopoo1;

import java.util.Scanner;
import trabalhopoo1.gui.controllers.ConsultaClientesController;
import trabalhopoo1.gui.views.ConsultaClientes;
import trabalhopoo1.gui.views.FormularioClientes;
import trabalhopoo1.gui.views.MenuPrincipal;
import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.controllers.FormularioClientesController;
import trabalhopoo1.gui.controllers.RemocaoClientesController;
import trabalhopoo1.gui.views.RemocaoClientes;

// Autores: Bruno Yozo RA: 140076 | Vitor Bispo RA: 138475

/**
 * Classe principal
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ViewPrincipal view;
    private static MenuPrincipal menuPrincipal;
    private static FormularioClientes formClientes;
    private static ConsultaClientes consClientes;
    private static RemocaoClientes remoClientes;
    
    private static FormularioClientesController formClientesController;
    private static ConsultaClientesController consClientesController;
    private static RemocaoClientesController remoClientesController;
    
    public static void main(String[] args) {
        view = new ViewPrincipal();
        
        formClientesController = new FormularioClientesController(view);
        consClientesController = new ConsultaClientesController(view);
        remoClientesController = new RemocaoClientesController(view);
        
        menuPrincipal = new MenuPrincipal();
        formClientes = new FormularioClientes(formClientesController);
        consClientes = new ConsultaClientes(consClientesController);
        remoClientes = new RemocaoClientes(remoClientesController);
        
        view.adicionarPainelCentral(menuPrincipal, "MenuPrincipal");
        view.adicionarPainelCentral(formClientes, "FormularioClientes");
        view.adicionarPainelCentral(consClientes, "ConsultaClientes");
        view.adicionarPainelCentral(remoClientes, "RemocaoClientes");
        
        view.mudarPainelCentral("MenuPrincipal");
    }

    public static ViewPrincipal getView() {
        return view;
    } 
}
