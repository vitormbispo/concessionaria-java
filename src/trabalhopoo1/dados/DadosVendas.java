package trabalhopoo1.dados;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import trabalhopoo1.excecoes.EntradaInvalidaException;

public class DadosVendas {
    private static final List<Venda> vendas = new ArrayList<>();

    public static List<Venda> getVendas() {
        return vendas;
    }
    
    /**
     * Cadastra uma nova venda
     * @param venda Venda a cadastrar
     */
    public static void cadastrar(Venda venda) {
        vendas.add(venda);
        System.out.println("Venda cadastrada com sucesso!");
    }

    /**
     * Consulta por uma venda através do CPF do cliente e do nome/chassi do veículo
     * @param cpfCliente CPF do cliente
     * @param nomeVeiculo Nome/chassi do veículo
     * @return 
     */
    public static ArrayList<Venda> consultar(String cpfCliente, String nomeVeiculo) {
        ArrayList<Venda> encontradas = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getCliente().getCpf().equals(cpfCliente) && 
                    (venda.getVeiculo().getModelo().equalsIgnoreCase(nomeVeiculo) || 
                    venda.getVeiculo().getChassi().equalsIgnoreCase(nomeVeiculo))) {
                encontradas.add(venda);
            }
        }
        if(encontradas.isEmpty()) {
            System.out.println("Venda não encontrada.");
        }
        return encontradas;
    }
    
    /**
     * Altera os dados de uma venda
     * @param venda Venda atualizada
     * @param data Data atualizada
     * @param novoValor Valor atualizado
     * @param novoCliente Cliente atualizado
     * @param novoFuncionario Funcionario atualizado
     * @param novoVeiculo Veiculo atualizado
     */
    public static void alterar(Venda venda, LocalDate data, double novoValor, Cliente novoCliente, Funcionario novoFuncionario, Veiculo novoVeiculo) {
        venda.setData(data);
        venda.setValor(novoValor);
        venda.setCliente(novoCliente);
        venda.setFuncionario(novoFuncionario);
        venda.setVeiculo(novoVeiculo);
    }

    /**
     * Remove a venda
     * @param venda Venda a ser removida
     */
    public static void remover(Venda venda) {
        if(vendas.contains(venda)) {
            vendas.remove(venda);
            System.out.println("Venda removida com sucesso!");
        } else
            System.out.println("Venda não encontrada!");
    }
    
    /**
     * Remove todas as vendas cadastradas
     */
    public static void removerTodos() {
        vendas.removeAll(vendas);
    }
    
     /**
     * Verifica se não há cadastros de vendas
     * @return {@code true} se nenhuma venda foi cadastrada.
     */
    public static boolean semCadastros() {
        return vendas.isEmpty();
    }
    
    /**
     * Verifica se determinado cliente possui alguma venda cadastrada
     * @param cpfCliente CPF do cliente
     * @return {@code true} se houver pelo menos uma venda cadastrada com esse cliente
     */
    public static boolean clientePossuiVenda(String cpfCliente) {
        for(Venda venda : vendas) {
            if(venda.getCliente().getCpf().equals(cpfCliente)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se determinado funcionário possui alguma venda cadastrada
     * @param matricula Nº Matrícula do funcionário
     * @return {@code true} se houver pelo menos uma venda cadastrada com esse funcionário
     */
    public static boolean funcionarioPossuiVenda(int matricula) {
        for(Venda venda : vendas) {
            if(venda.getFuncionario().getNumeroMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se determinado veículo possui alguma venda cadastrada
     * @param veiculo Veiculo a verificar
     * @return {@code true} se houver pelo menos uma venda cadastrada com esse veículo
     */
    public static boolean veiculoPossuiVenda(Veiculo veiculo) {
        for(Venda venda : vendas) {
            if(venda.getVeiculo().equals(veiculo)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se existe alguma venda cadastrada com um veículo de determinado nome ou chassi
     * @param nome Nome ou chassi do veículo
     * @return {@code true} se houver pelo menos uma venda cadastrada com esse nome de veículo
     */
    public static boolean veiculoPossuiVenda(String nome) {
        for(Venda venda : vendas) {
            if(venda.getVeiculo().getModelo().equalsIgnoreCase(nome) || venda.getVeiculo().getChassi().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }
    
    // Validações
    
    public static boolean validarCliente(String cpf) {
        boolean valido = false;
        
        if(cpf.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(!DadosClientes.cpfExiste(cpf))
            throw new EntradaInvalidaException("Nenhum cliente com esse CPF!");
        else
            valido = true;
        
        return valido;
    }
    
    
    public static boolean validarFuncionario(String numMatricula) {
        boolean valido = false;
        
        if(numMatricula.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try{
            long convertido = Long.parseLong(numMatricula);
            if(!DadosFuncionarios.matriculaExiste(convertido))
                throw new EntradaInvalidaException("Nenhum funcionário com essa matrícula!");
            else
                valido = true;
        } catch(NumberFormatException e) {
            throw new EntradaInvalidaException("Nº de matrícula inválido!");
        }
        
        return valido;
    }
    
    public static boolean validarVeiculo(String chassi) {
        boolean valido = false;
        
        if(chassi.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(!DadosVeiculos.chassiExiste(chassi))
            throw new EntradaInvalidaException("Nenhum veículo com esse chassi!");
        else
            valido = true;
        
        return valido;
    }
    
    public static boolean validarData(String data) {
        boolean valido = false;
        
        if(data.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            if(LocalDate.parse(data) != null)
                valido = true;
        } catch (DateTimeParseException e) {
            throw new EntradaInvalidaException("Formato inválido! (AAAA-MM-DD)");
        }
        
        return valido;
    }
    
    public static boolean validarValor(String valor) {
        boolean valido = false;
        
        if(valor.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            double convertido = Double.parseDouble(valor);
            if(convertido < 0)
                throw new EntradaInvalidaException("Valor inválido!");
            else
                valido = true;
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Deve ser um número! (Ex.: 12.34)");
        }
        
        return valido;
    }
    
    /**
     * Redireciona todas as referências das vendas a um determinado cliente para outro.
     * @param antigo Referência substituída
     * @param novo Nova referência
     */
    public static void redirecionarReferencias(Cliente antigo, Cliente novo) {
        for(Venda venda : vendas) {
            if(venda.getCliente().equals(antigo))
                venda.setCliente(novo);
        }
    }
    
    /**
     * Redireciona todas as referências das vendas a um determinado funcionário para outro.
     * @param antigo Referência substituída
     * @param novo Nova referência
     */
    public static void redirecionarReferencias(Funcionario antigo, Funcionario novo) {
        for(Venda venda : vendas) {
            if(venda.getFuncionario().equals(antigo))
                venda.setFuncionario(novo);
        }
    }
    
    /**
     * Redireciona todas as referências das vendas a um determinado veículo para outro.
     * @param antigo Referência substituída
     * @param novo Nova referência
     */
    public static void redirecionarReferencias(Veiculo antigo,Veiculo novo) {
        for(Venda venda : vendas) {
            if(venda.getVeiculo().equals(antigo))
                venda.setVeiculo(novo);
        }
    }  
}
