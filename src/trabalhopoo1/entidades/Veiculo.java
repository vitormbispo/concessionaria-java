package trabalhopoo1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para objetos do tipo Veículo
 */
@Entity
@Table(name="Veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name="modelo", length=30)
    private String modelo;
    @Column(name="cor", length=20)
    private String cor;
    @Column(name="numMarchas", length=2)
    private int numMarchas;
    @Column(name="numPortas", length=2)
    private int numPortas;
    @Column(name="marca", length=30)
    private String marca;
    @Column(name="ano")
    private int ano;
    @Column(name="chassi",length=20, unique=true)
    private String chassi;

    public Veiculo() {
    }

    public Veiculo(String modelo, String marca, String cor, int ano, int numMarchas, int numPortas, String chassi) {
        this.modelo = modelo;
        this.cor = cor;
        this.numMarchas = numMarchas;
        this.numPortas = numPortas;
        this.marca = marca;
        this.ano = ano;
        this.chassi = chassi;
    }
    
    public Veiculo clone() {
        return new Veiculo(this.modelo,this.cor,this.marca,this.ano,this.numMarchas,this.numPortas,this.chassi);
    }

    public long getId() { return id; }
    public String getModelo() { return modelo; }
    public String getCor() { return cor; }
    public int getNumMarchas() { return numMarchas; }
    public int getNumPortas() { return numPortas; }
    public String getMarca() { return marca; }
    public int getAno() { return ano; }
    public String getChassi() { return chassi; }
    
    public void setModelo(String nome) { this.modelo = nome; }
    public void setCor(String cor) { this.cor = cor; }
    public void setNumMarchas(int numMarchas) { this.numMarchas = numMarchas; }
    public void setNumPortas(int numPortas) { this.numPortas = numPortas; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAno(int ano) { this.ano = ano; }
    public void setChassi(String chassi) { this.chassi = chassi; }
    
    public String nomeComposto() {
        return String.format("%s-%s",marca,modelo);
    }
    
    @Override
    public String toString() {
        return String.format("%s | %s | %s",
                modelo, marca, ano);
    }
    
    @Override
    public boolean equals(Object obj) {
        Veiculo outro = (Veiculo) obj;
        return (outro.getChassi().equalsIgnoreCase(this.chassi));
    }
}
