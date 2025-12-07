package trabalhopoo1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para objetos do tipo Funcionario
 */
@Entity
@Table (name="Funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(name="nome", length=100, nullable=false)
    private String nome;
    
    @Column(name="numMatricula", length=20, nullable=false, unique=true)
    private long numeroMatricula;
    
    @Column(name="qualificaçao", length=50, nullable=false)
    private String qualificacao;
    
    @Column(name="descFuncao", length=200, nullable=false)
    private String descFuncao;
    
    @Column(name="cargaHoraria", length=2, nullable=false)
    private int cargaHoraria;

    public Funcionario() {
    }
    
    public Funcionario(String nome, long numeroMatricula, String qualificacao, String descFuncao, int cargaHoraria) {
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
        this.qualificacao = qualificacao;
        this.descFuncao = descFuncao;
        this.cargaHoraria = cargaHoraria;
    }
    
    public Funcionario clone() {
        return new Funcionario(this.nome,this.numeroMatricula,this.qualificacao,this.descFuncao,this.cargaHoraria);
    }
    
    public long getId() { return id; }
    public String getNome() { return nome; }
    public long getNumeroMatricula() { return numeroMatricula; }
    public String getQualificacao() { return qualificacao; }
    public String getDescFuncao() { return descFuncao; }

    public void setNome(String nome) { this.nome = nome; }
    public void setMatricula(long matricula) { this.numeroMatricula = matricula; }
    public void setQualificacao(String qualificacao) { this.qualificacao = qualificacao; }
    public void setDescFuncao(String descFuncao) { this.descFuncao = descFuncao; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    
    @Override
    public String toString() {
        return this.nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        Funcionario outro = (Funcionario) obj;
        return this.numeroMatricula == outro.getNumeroMatricula();
    }
}
