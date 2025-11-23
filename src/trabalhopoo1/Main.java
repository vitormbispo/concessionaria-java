package trabalhopoo1;

import java.util.Scanner;
import trabalhopoo1.gui.controllers.ConsultaClientesController;
import trabalhopoo1.gui.views.ConsultaClientes;
import trabalhopoo1.gui.views.FormularioClientes;
import trabalhopoo1.gui.views.MenuPrincipal;
import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.controllers.FormularioClientesController;

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
    
    private static FormularioClientesController formClientesController;
    private static ConsultaClientesController consClientesController;
    
    public static void main(String[] args) {
        view = new ViewPrincipal();
        
        formClientesController = new FormularioClientesController(view);
        consClientesController = new ConsultaClientesController(view);
        
        menuPrincipal = new MenuPrincipal(view);
        formClientes = new FormularioClientes(formClientesController);
        consClientes = new ConsultaClientes(consClientesController);
        
        formClientesController.setFormulario(formClientes);
        
        view.adicionarPainelCentral(menuPrincipal, "MenuPrincipal");
        view.adicionarPainelCentral(formClientes, "FormularioClientes");
        view.adicionarPainelCentral(consClientes, "ConsultaClientes");
        
        view.mudarPainelCentral("MenuPrincipal");
    }

    public static ViewPrincipal getView() {
        return view;
    } 

    public static MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public static FormularioClientes getFormClientes() {
        return formClientes;
    }

    public static ConsultaClientes getConsClientes() {
        return consClientes;
    }


    public static FormularioClientesController getFormClientesController() {
        return formClientesController;
    }

    public static ConsultaClientesController getConsClientesController() {
        return consClientesController;
    }
}
