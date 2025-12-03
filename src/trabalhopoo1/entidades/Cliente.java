package trabalhopoo1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para objetos do tipo Cliente
 */
@Entity
@Table(name="Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(length=50, name="nome", nullable=false)
    private String nome;
    @Column(length=20, name="telefone", nullable=false)
    private String telefone;
    @Column(length=320, name="email", nullable=false)
    private String email;
    @Column(length=20, name="cpf", nullable=false)
    private String cpf;
    @Column(length=20, name="rg", nullable=false)
    private String rg;

    public Cliente() {
    }

    
    public Cliente(String nome, String telefone, String email, String cpf,String rg) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        
    }
    
    public Cliente clone() {
        return new Cliente(this.nome,this.telefone,this.email,this.rg,this.cpf);
    }
    
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getRg() { return rg; }
    public String getCpf() { return cpf; }
    
    public void setId(long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setRg(String rg) { this.rg = rg; }
    public void setCpf(String cpf) {this.cpf = cpf; }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        Cliente outro = (Cliente) obj;
        return this.id == outro.getId();
    }
}
