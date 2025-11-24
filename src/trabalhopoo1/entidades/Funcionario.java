package trabalhopoo1.entidades;

/**
 * Classe para objetos do tipo Funcionario
 * @author Vitor Bispo
 */
public class Funcionario {
    private String nome;
    private long numeroMatricula;
    private String qualificacao;
    private String descFuncao;
    private int cargaHoraria;

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
        return this.numeroMatricula == outro.numeroMatricula;
    }
}
