package trabalhopoo1.gui.controllers.vendas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextField;
import trabalhopoo1.dados.DadosClientes;
import trabalhopoo1.dados.DadosFuncionarios;
import trabalhopoo1.dados.DadosVeiculos;
import trabalhopoo1.dados.DadosVendas;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.excecoes.EntradaInvalidaException;

import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.views.vendas.FormularioVendas;

public class FormularioVendasController {
    private final ViewPrincipal viewPrincipal;
    private FormularioVendas formulario;
    
    public FormularioVendasController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Cadastra uma nova venda
     * @param cliente CPF do cliente da cenda
     * @param funcionario Numero de matrícula do funcionário
     * @param veiculo Chassi do veículo
     * @param data Data da venda
     * @param valor Valor da venda
     */
    public void cadastrarVenda(String cliente, String funcionario, String veiculo, String data, String valor) {
        if(!validarEntradas(cliente,funcionario,veiculo,data,valor))
            return;
        
        Venda venda = new Venda(
                LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE),
                Double.parseDouble(valor),
                DadosClientes.consultar(cliente),
                DadosFuncionarios.consultar(Long.parseLong(funcionario)),
                DadosVeiculos.consultar(veiculo));
                
        
        DadosVendas.cadastrar(venda);
        
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoVendas().adicionarObjeto(venda);
        viewPrincipal.mudarPainelCentral("ConsultaVendas");
        
    }
    
    /**
     * Modifica uma venda
     * @param venda Venda a modificar
     * @param cliente CPF do cliente
     * @param funcionario Numero de matrícula do funcionário
     * @param veiculo Chassi do veículo
     * @param data Data da venda
     * @param valor Valor da venda
     */
    public void modificarVenda(Venda venda,String cliente, String funcionario, String veiculo, String data, String valor) {
        boolean entradasValidas = true;
        
        if(!venda.getCliente().getCpf().equals(cliente))
            entradasValidas = entradasValidas & validarCliente(cliente);
        if(!Long.toString(venda.getFuncionario().getNumeroMatricula()).equals(funcionario))
            entradasValidas = entradasValidas & validarFuncionario(funcionario);
        if(!venda.getVeiculo().getChassi().equals(veiculo))
            entradasValidas = entradasValidas & validarVeiculo(veiculo);
        if(!venda.getData().toString().equals(data))
            entradasValidas = entradasValidas & validarData(data);
        if(!Double.toString(venda.getValor()).equals(valor))
            entradasValidas = entradasValidas & validarValor(valor);
        
        if(!entradasValidas)
            return;
        
        Venda novaVenda = new Venda(
                LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE),
                Double.parseDouble(valor),
                DadosClientes.consultar(cliente),
                DadosFuncionarios.consultar(Long.parseLong(funcionario)),
                DadosVeiculos.consultar(veiculo));
        
        viewPrincipal.getArvore().getNoVendas().alterarObjeto(venda, novaVenda);
        DadosVendas.alterar(venda, novaVenda.getData(), novaVenda.getValor(), novaVenda.getCliente(), novaVenda.getFuncionario(), novaVenda.getVeiculo());
        formulario.reiniciar();
        viewPrincipal.mudarPainelCentral("ConsultaVendas");
    }
    
    // Validações
    
    public boolean validarCliente(String cliente) {
        boolean valido = true;
        
        try {
            valido = DadosVendas.validarCliente(cliente);
            formulario.getjLabelClienteVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelClienteVal().setText(e.getMessage());
            formulario.getjLabelClienteVal().setVisible(true);
        }

        return valido;
    }
    
    public boolean validarVeiculo(String veiculo) {
        boolean valido = true;
        
        try {
            valido = DadosVendas.validarVeiculo(veiculo);
            formulario.getjLabelVeiculoVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelVeiculoVal().setText(e.getMessage());
            formulario.getjLabelVeiculoVal().setVisible(true);
        }

        return valido;
    }
    
     public boolean validarFuncionario(String funcionario) {
        boolean valido = true;
        
        try {
            valido = DadosVendas.validarFuncionario(funcionario);
            formulario.getjLabelFuncionarioVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelFuncionarioVal().setText(e.getMessage());
            formulario.getjLabelFuncionarioVal().setVisible(true);
        }

        return valido;
    }
    
    public boolean validarData(String data) {
        boolean valido = true;
        
        try {
            valido = DadosVendas.validarData(data);
            formulario.getjLabelDataVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelDataVal().setText(e.getMessage());
            formulario.getjLabelDataVal().setVisible(true);
        }

        return valido;
    }
       
    public boolean validarValor(String valor) {
        boolean valido = true;
        
        try {
            valido = DadosVendas.validarValor(valor);
            formulario.getjLabelValorVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelValorVal().setText(e.getMessage());
            formulario.getjLabelValorVal().setVisible(true);
        }

        return valido;
    }
    
    public boolean validarEntradas(String cliente, String funcionario, String veiculo, String data, String valor) {
        return validarCliente(cliente) &
               validarFuncionario(funcionario) &
               validarVeiculo(veiculo) &
               validarData(data) &
               validarValor(valor);
    }
    
    /**
     * Volta para a tela de consulta de veículos
     */
    public void cancelar() {
        viewPrincipal.mudarPainelCentral("ConsultaVendas");
    }
    
    public void atualizarCaixas() {
        atualizarCaixaClientes();
        atualizarCaixaFuncionarios();
        atualizarCaixaVeiculos();
    }
    
    public void atualizarCaixaClientes() {
        formulario.getjComboBoxClientes().removeAllItems();
        DadosClientes.getClientes().forEach((cliente) -> {
            formulario.getjComboBoxClientes().addItem(cliente.getNome());
        });
    }
    
    public void atualizarCaixaFuncionarios() {
        formulario.getjComboBoxFuncionarios().removeAllItems();
        DadosFuncionarios.getFuncionarios().forEach((funcionario) -> {
            formulario.getjComboBoxFuncionarios().addItem(funcionario.getNome());
        });
    }
    
    public void atualizarCaixaVeiculos() {
        formulario.getjComboBoxVeiculos().removeAllItems();
        DadosVeiculos.getVeiculos().forEach((veiculo) -> {
            formulario.getjComboBoxVeiculos().addItem(veiculo.toString());
        });  
    }
    
    public void selecionarCliente(int indice) {
        if(indice == -1)
            return;
        
        formulario.getjTextFieldCliente()
                .setText(DadosClientes.getClientes().get(indice).getCpf());
    }
    
    public void selecionarFuncionario(int indice) {
        formulario.getjTextFieldFuncionario()
                .setText(Long.toString(DadosFuncionarios.getFuncionarios().get(indice).getNumeroMatricula()));
    }
    
    public void selecionarVeiculo(int indice) {
        formulario.getjTextFieldVeiculo()
                .setText(DadosVeiculos.getVeiculos().get(indice).getChassi());
    }
    
    public void setFormulario(FormularioVendas formulario) {
        this.formulario = formulario;
    }
}
