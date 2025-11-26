package trabalhopoo1.gui.controllers.veiculos;

import trabalhopoo1.dados.DadosVeiculos;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.excecoes.EntradaInvalidaException;

import trabalhopoo1.gui.views.ViewPrincipal;
import trabalhopoo1.gui.views.veiculos.FormularioVeiculos;

public class FormularioVeiculosController {
    private final ViewPrincipal viewPrincipal;
    private FormularioVeiculos formulario;
    
    public FormularioVeiculosController(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    /**
     * Cadastra um novo veículo
     * @param modelo Modelo do veículo
     * @param marca Numero de matrícula do veículo
     * @param cor Cor do veículo
     * @param ano Ano do veículo
     * @param numMarchas Número de marchas do veículo
     * @param numPortas Número de portas do veículo
     * @param chassi Chassi do veículo
     */
    public void cadastrarVeiculo(String modelo, String marca,String cor, String ano, String numMarchas, String numPortas, String chassi) {
        if(!validarEntradas(modelo,marca,cor,ano,numMarchas,numPortas,chassi))
            return;
        
        Veiculo veiculo = new Veiculo(modelo,marca,cor,Integer.parseInt(ano),Integer.parseInt(numMarchas),Integer.parseInt(numPortas),chassi);
        
        DadosVeiculos.cadastrar(veiculo);
        
        formulario.reiniciar();
        viewPrincipal.getArvore().getNoVeiculos().adicionarObjeto(veiculo);
        viewPrincipal.mudarPainelCentral("ConsultaVeiculos");
        
    }
    
    /**
     * Modifica um veiculo
     * @param veiculo Veículo a modificar
     * @param modelo Modelo do veículo
     * @param marca Numero de matrícula do veículo
     * @param cor Cor do veículo
     * @param ano Ano do veículo
     * @param numMarchas Número de marchas do veículo
     * @param numPortas Número de portas do veículo
     * @param chassi Chassi do veículo
     */
    public void modificarVeiculo(Veiculo veiculo,String modelo, String marca, String cor, String ano, String numMarchas, String numPortas, String chassi) {
        boolean entradasValidas = true;
        
        if(!veiculo.getModelo().equals(modelo))
            entradasValidas = entradasValidas & validarModelo(modelo);
        if(!veiculo.getMarca().equals(marca))
            entradasValidas = entradasValidas & validarMarca(marca);
        if(!veiculo.getCor().equals(cor))
            entradasValidas = entradasValidas & validarCor(cor);
        if(!Integer.toString(veiculo.getAno()).equals(ano))
            entradasValidas = entradasValidas & validarAno(ano);
        if(!Integer.toString(veiculo.getNumMarchas()).equals(numMarchas))
            entradasValidas = entradasValidas & validarNumMarchas(numMarchas);
        if(!Integer.toString(veiculo.getNumPortas()).equals(numPortas))
            entradasValidas = entradasValidas & validarNumPortas(numPortas);
        if(!veiculo.getChassi().equals(chassi))
            entradasValidas = entradasValidas & validarChassi(chassi);
        
        if(!entradasValidas)
            return;
        
        Veiculo novoVeiculo = new Veiculo(modelo, marca, cor, Integer.parseInt(ano), Integer.parseInt(numMarchas), Integer.parseInt(numPortas), chassi);
        viewPrincipal.getArvore().getNoVeiculos().alterarObjeto(veiculo, novoVeiculo);
        DadosVeiculos.alterar(veiculo, modelo, marca, cor, novoVeiculo.getAno(), novoVeiculo.getNumMarchas(), novoVeiculo.getNumPortas(), chassi);
        formulario.reiniciar();
        viewPrincipal.mudarPainelCentral("ConsultaVeiculos");
    }
    
    // Validações
    
    public boolean validarModelo(String modelo) {
        boolean valido = true;
        
        if(modelo.isBlank()) {
            valido = false;
            formulario.getjLabelModeloVal().setText("O campo não pode estar vazio!");
            formulario.getjLabelModeloVal().setVisible(true);
        } else {
            formulario.getjLabelModeloVal().setVisible(false);
        }

        return valido;
    }
    
    public boolean validarCor(String cor) {
        boolean valido = true;
        
        if(cor.isBlank()) {
            valido = false;
            formulario.getjLabelCorVal().setText("O campo não pode estar vazio!");
            formulario.getjLabelCorVal().setVisible(true);
        } else {
            formulario.getjLabelCorVal().setVisible(false);
        }

        return valido;
    }
    
     public boolean validarMarca(String cor) {
        boolean valido = true;
        
        if(cor.isBlank()) {
            valido = false;
            formulario.getjLabelMarcaVal().setText("O campo não pode estar vazio!");
            formulario.getjLabelMarcaVal().setVisible(true);
        } else {
            formulario.getjLabelMarcaVal().setVisible(false);
        }

        return valido;
    }
    
    public boolean validarAno(String ano) {
        boolean valido = true;
        
        try {
            valido = DadosVeiculos.validarAno(ano);
            formulario.getjLabelAnoVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelAnoVal().setText(e.getMessage());
            formulario.getjLabelAnoVal().setVisible(true);
        }

        return valido;
    }
       
    public boolean validarNumMarchas(String numMarchas) {
        boolean valido = true;
        
        try {
            valido = DadosVeiculos.validarNumMarchas(numMarchas);
            formulario.getjLabelNMarchasVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelNMarchasVal().setText(e.getMessage());
            formulario.getjLabelNMarchasVal().setVisible(true);
        }
        
        return valido;
    }
    
    public boolean validarNumPortas(String numPortas) {
        boolean valido = true;
        
        try {
            valido = DadosVeiculos.validarNumPortas(numPortas);
            formulario.getjLabelNPortasVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelNPortasVal().setText(e.getMessage());
            formulario.getjLabelNPortasVal().setVisible(true);
        }
        
        return valido;
    }
    
    public boolean validarChassi(String chassi) {
        boolean valido = true;
        
        try {
            valido = DadosVeiculos.validarChassi(chassi);
            formulario.getjLabelChassiVal().setVisible(false);
        } catch(EntradaInvalidaException e) {
            valido = false;
            formulario.getjLabelChassiVal().setText(e.getMessage());
            formulario.getjLabelChassiVal().setVisible(true);
        }
        
        return valido;
    }
    
    public boolean validarEntradas(String modelo, String marca, String cor, String ano, String numMarchas, String numPortas, String chassi) {
        return validarModelo(modelo) &
               validarMarca(marca) &
               validarCor(cor) &
               validarAno(ano) &
               validarNumMarchas(numMarchas) &
               validarNumPortas(numPortas) &
               validarChassi(chassi);
    }
    
    /**
     * Volta para a tela de consulta de veículos
     */
    public void cancelar() {
        viewPrincipal.mudarPainelCentral("ConsultaVeiculos");
    }
    
    public void setFormulario(FormularioVeiculos formulario) {
        this.formulario = formulario;
    }
}
