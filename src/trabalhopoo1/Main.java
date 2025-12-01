package trabalhopoo1;

import java.util.Scanner;
import trabalhopoo1.dados.BancoDados;
import trabalhopoo1.gui.controllers.clientes.ConsultaClientesController;
import trabalhopoo1.gui.views.MenuPrincipal;
import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.controllers.clientes.FormularioClientesController;
import trabalhopoo1.gui.controllers.funcionarios.ConsultaFuncionariosController;
import trabalhopoo1.gui.controllers.funcionarios.FormularioFuncionariosController;
import trabalhopoo1.gui.controllers.veiculos.ConsultaVeiculosController;
import trabalhopoo1.gui.controllers.veiculos.FormularioVeiculosController;
import trabalhopoo1.gui.controllers.vendas.ConsultaVendasController;
import trabalhopoo1.gui.controllers.vendas.FormularioVendasController;
import trabalhopoo1.gui.views.clientes.ConsultaClientes;
import trabalhopoo1.gui.views.clientes.FormularioClientes;
import trabalhopoo1.gui.views.funcionarios.ConsultaFuncionarios;
import trabalhopoo1.gui.views.funcionarios.FormularioFuncionarios;
import trabalhopoo1.gui.views.veiculos.ConsultaVeiculos;
import trabalhopoo1.gui.views.veiculos.FormularioVeiculos;
import trabalhopoo1.gui.views.vendas.ConsultaVendas;
import trabalhopoo1.gui.views.vendas.FormularioVendas;

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
    
    private static FormularioFuncionarios formFuncionarios;
    private static ConsultaFuncionarios consFuncionarios;
    
    private static FormularioVeiculos formVeiculos;
    private static ConsultaVeiculos consVeiculos;
    
    private static FormularioVendas formVendas;
    private static ConsultaVendas consVendas;
    
    private static FormularioClientesController formClientesController;
    private static ConsultaClientesController consClientesController;
    
    private static FormularioFuncionariosController formFuncionariosController;
    private static ConsultaFuncionariosController consFuncionariosController;
    
    private static FormularioVeiculosController formVeiculosController;
    private static ConsultaVeiculosController consVeiculosController;
    
    private static FormularioVendasController formVendasController;
    private static ConsultaVendasController consVendasController;
    
    private static String telaAtual;
    
    public static void main(String[] args) {
        BancoDados.iniciar();
        view = new ViewPrincipal();
        
        formClientesController = new FormularioClientesController(view);
        consClientesController = new ConsultaClientesController(view);
        
        formFuncionariosController = new FormularioFuncionariosController(view);
        consFuncionariosController = new ConsultaFuncionariosController(view);
        
        formVeiculosController = new FormularioVeiculosController(view);
        consVeiculosController = new ConsultaVeiculosController(view);
        
        formVendasController = new FormularioVendasController(view);
        consVendasController = new ConsultaVendasController(view);
        
        menuPrincipal = new MenuPrincipal(view);
        
        formClientes = new FormularioClientes(formClientesController);
        consClientes = new ConsultaClientes(consClientesController);
        
        formFuncionarios = new FormularioFuncionarios(formFuncionariosController);
        consFuncionarios = new ConsultaFuncionarios(consFuncionariosController);
        
        formVeiculos = new FormularioVeiculos(formVeiculosController);
        consVeiculos = new ConsultaVeiculos(consVeiculosController);
        
        formVendas = new FormularioVendas(formVendasController);
        consVendas = new ConsultaVendas(consVendasController);
        
        formClientesController.setFormulario(formClientes);
        formFuncionariosController.setFormulario(formFuncionarios);
        formVeiculosController.setFormulario(formVeiculos);
        formVendasController.setFormulario(formVendas);
        
        view.adicionarPainelCentral(menuPrincipal, "MenuPrincipal");
        view.adicionarPainelCentral(formClientes, "FormularioClientes");
        view.adicionarPainelCentral(consClientes, "ConsultaClientes");
        view.adicionarPainelCentral(formFuncionarios, "FormularioFuncionarios");
        view.adicionarPainelCentral(consFuncionarios, "ConsultaFuncionarios");
        view.adicionarPainelCentral(formVeiculos, "FormularioVeiculos");
        view.adicionarPainelCentral(consVeiculos, "ConsultaVeiculos");
        view.adicionarPainelCentral(formVendas, "FormularioVendas");
        view.adicionarPainelCentral(consVendas, "ConsultaVendas");
        
        
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

    public static FormularioFuncionarios getFormFuncionarios() {
        return formFuncionarios;
    }

    public static ConsultaFuncionarios getConsFuncionarios() {
        return consFuncionarios;
    }

    public static FormularioFuncionariosController getFormFuncionariosController() {
        return formFuncionariosController;
    }

    public static ConsultaFuncionariosController getConsFuncionariosController() {
        return consFuncionariosController;
    }

    public static FormularioVeiculos getFormVeiculos() {
        return formVeiculos;
    }

    public static ConsultaVeiculos getConsVeiculos() {
        return consVeiculos;
    }

    public static FormularioVeiculosController getFormVeiculosController() {
        return formVeiculosController;
    }

    public static ConsultaVeiculosController getConsVeiculosController() {
        return consVeiculosController;
    }

    public static FormularioVendas getFormVendas() {
        return formVendas;
    }

    public static ConsultaVendas getConsVendas() {
        return consVendas;
    }

    public static FormularioVendasController getFormVendasController() {
        return formVendasController;
    }

    public static ConsultaVendasController getConsVendasController() {
        return consVendasController;
    }

    public static String getTelaAtual() {
        return telaAtual;
    }

    public static void setTelaAtual(String telaAtual) {
        Main.telaAtual = telaAtual;
    }
}
