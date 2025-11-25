package trabalhopoo1.entidades;

public class Veiculo {
    private String modelo;
    private String cor;
    private int numMarchas;
    private int numPortas;
    private String marca;
    private int ano;
    private String chassi;

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

    public String getModelo() { return modelo; }
    public String getCor() { return cor; }
    public int getNumMarchas() { return numMarchas; }
    public int getNumPortas() { return numPortas; }
    public String getMarca() { return marca; }
    public int getAno() { return ano; }
    public String getChassi() { return chassi; };
    
    public void setModelo(String nome) { this.modelo = nome; }
    public void setCor(String cor) { this.cor = cor; }
    public void setNumMarchas(int numMarchas) { this.numMarchas = numMarchas; }
    public void setNumPortas(int numPortas) { this.numPortas = numPortas; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAno(int ano) { this.ano = ano; }
    public void setChassi(String chassi) { this.chassi = chassi; }
    
    public String nomeComposto() {
        return String.format("%s-%s-%s-%s",modelo,cor,ano,chassi);
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
