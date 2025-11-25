package trabalhopoo1.entidades;

import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private double valor;
    private Cliente cliente;
    private Funcionario funcionario;
    private Veiculo veiculo;

    public Venda(LocalDate data, double valor, Cliente cliente, Funcionario funcionario, Veiculo veiculo) {
        this.data = data;
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
    }

    public LocalDate getData() { return data; }
    public double getValor() { return valor; }
    public Cliente getCliente() { return cliente; }
    public Funcionario getFuncionario() { return funcionario; }
    public Veiculo getVeiculo() { return veiculo; }

    public void setData(LocalDate data) { this.data = data; }
    public void setValor(double valor) { this.valor = valor; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    @Override
    public String toString() {
        return String.format("Cliente: %s | Veículo: %s | Funcionário:%s | Valor: R$ %.2f | Data: %s | ",
                 cliente.getNome(), veiculo.nomeComposto(), funcionario.getNome(), valor, data.toString());
    }
    
    public String nomeResumido() {
        return String.format("%s | %s", cliente.getNome(),veiculo.getModelo());
    }
}
