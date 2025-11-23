package trabalhopoo1.gui.controllers;

import trabalhopoo1.Main;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.entidades.Cliente;
import trabalhopoo1.excecoes.EntradaInvalidaException;
import trabalhopoo1.gui.views.FormularioClientes;
import trabalhopoo1.gui.views.ViewPrincipal;

public class FormularioClientesController {
    private ViewPrincipal viewPrincipal;
    private FormularioClientes formulario;
    
    public FormularioClientesController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    public void cadastrarCliente(String nome, String telefone,String email, String cpf, String rg) {
        if(!validarEntradas(nome,telefone,email,cpf,rg))
            return;
        
        Cliente cliente = new Cliente(nome,telefone,email,cpf,rg);
        
        DadosClientes.cadastrar(cliente);
        
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoClientes().adicionarObjeto(cliente);
        viewPrincipal.mudarPainelCentral("ConsultaClientes");
        
    }
    
    public void modificarCliente(Cliente cliente,String nome, String telefone, String email, String cpf, String rg) {
        boolean entradasValidas = true;
        
        if(!cliente.getNome().equals(nome))
            entradasValidas = entradasValidas & validarNome(nome);
        if(!cliente.getTelefone().equals(telefone))
            entradasValidas = entradasValidas & validarTelefone(telefone);
        if(!cliente.getEmail().equals(email))
            entradasValidas = entradasValidas & validarEmail(email);
        if(!cliente.getCpf().equals(cpf))
            entradasValidas = entradasValidas & validarCpf(cpf);
        if(!cliente.getRg().equals(rg))
            entradasValidas = entradasValidas & validarRg(rg);
        
        if(!entradasValidas)
            return;
        
        Cliente novoCliente = new Cliente(nome,telefone,email,cpf,rg);
        DadosClientes.alterar(cliente, nome, telefone, email, rg, cpf);
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoClientes().alterarObjeto(cliente, novoCliente);
        viewPrincipal.mudarPainelCentral("ConsultaClientes");
    }
    
    public boolean validarNome(String nome) {
        boolean valido = true;
        
        try {
            DadosClientes.validarNome(nome);
            formulario.getjLabelNomeVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelNomeVal().setText(e.getMessage());
            formulario.getjLabelNomeVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarTelefone(String telefone) {
        boolean valido = true;
        
        try {
            DadosClientes.validarTelefone(telefone);
            formulario.getjLabelTelefoneVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelTelefoneVal().setText(e.getMessage());
            formulario.getjLabelTelefoneVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarEmail(String email) {
        boolean valido = true;
        
        try {
            DadosClientes.validarEmail(email);
            formulario.getjLabelEmailVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelEmailVal().setText(e.getMessage());
            formulario.getjLabelEmailVal().setVisible(true);
        }
        return valido;
    }
       
    public boolean validarCpf(String cpf) {
        boolean valido = true;
        
        try {
            DadosClientes.validarCpf(cpf);
            formulario.getjLabelCPFVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelCPFVal().setText(e.getMessage());
            formulario.getjLabelCPFVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarRg(String rg) {
        boolean valido = true;
        
        try {
            DadosClientes.validarRg(rg);
            formulario.getjLabelRGVal().setVisible(false);
        } catch (EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelRGVal().setText(e.getMessage());
            formulario.getjLabelRGVal().setVisible(true);
        }
        return valido;
    }
    
    public boolean validarEntradas(String nome, String telefone, String email, String cpf, String rg) {
        return validarNome(nome) &
               validarTelefone(telefone) &
               validarEmail(email) &
               validarCpf(cpf) &
               validarRg(rg);
    }
    
    public void cancelar() {
        viewPrincipal.mudarPainelCentral("ConsultaClientes");
    }
    
    public void setFormulario(FormularioClientes formulario) {
        this.formulario = formulario;
    }
    
    
}
