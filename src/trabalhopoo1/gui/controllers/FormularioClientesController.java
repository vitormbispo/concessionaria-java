package trabalhopoo1.gui.controllers;

import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.gui.views.ViewPrincipal;

public class FormularioClientesController {
    private ViewPrincipal viewPrincipal;
    
    public FormularioClientesController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    public void cadastrarCliente(String nome, String telefone,String email, String cpf, String rg) {
        Cliente cliente = new Cliente(nome,telefone,email,cpf,rg);
        
        DadosClientes.cadastrar(cliente);
        viewPrincipal.getArvore().getNoClientes().adicionarObjeto(cliente);
        viewPrincipal.mudarPainelCentral("MenuPrincipal");
        
    }
    
    public void cancelar() {
        viewPrincipal.mudarPainelCentral("MenuPrincipal");
    }
}
