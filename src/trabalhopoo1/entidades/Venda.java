package trabalhopoo1.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe para objetos do tipo Venda
 */
@Entity
@Table(name="Venda")
public class Venda {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name="dataVenda", nullable=false)
    private LocalDate dataVenda;
    @Column(name="valor", nullable=false)
    private double valor;
    @ManyToOne
    @JoinColumn(name="cliente", nullable=true)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="funcionario", nullable=true)
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name="veiculo", nullable=true)
    private Veiculo veiculo;

    public Venda() {
    }
    
    public Venda(LocalDate data, double valor, Cliente cliente, Funcionario funcionario, Veiculo veiculo) {
        this.dataVenda = data;
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
    }

    public long getId() { return id; }
    public LocalDate getData() { return dataVenda; }
    public double getValor() { return valor; }
    public Cliente getCliente() { return cliente; }
    public Funcionario getFuncionario() { return funcionario; }
    public Veiculo getVeiculo() { return veiculo; }

    public void setData(LocalDate data) { this.dataVenda = data; }
    public void setValor(double valor) { this.valor = valor; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    @Override
    public String toString() {
        return String.format("%s | %s |",
                 cliente != null ? cliente.getNome() : "*", veiculo != null ? veiculo.nomeComposto() : "*");
    }
    
    public String nomeResumido() {
        return String.format("%s | %s |",
                 cliente != null ? cliente.getNome() : "*", veiculo != null ? veiculo.getModelo() : "*");
    }
}
