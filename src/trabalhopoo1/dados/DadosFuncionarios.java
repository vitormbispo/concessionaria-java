package trabalhopoo1.dados;

import trabalhopoo1.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class DadosFuncionarios {
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    public static List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Cadastra um novo funcionario.
     * @param funcionario Funcionario a ser cadastrado
     */
    public static void cadastrar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        DadosVendas.redirecionarReferencias(funcionario, funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    /**
     * Procura por um funcionário a partir do seu número de matrícula
     * @param matricula Nº dr matrrícula do funcionário a consultar
     * @return O objeto do funcionário consultado ou {@code null} caso não seja encontrado.
     */
    public static Funcionario consultar(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNumeroMatricula() == matricula) {
                return funcionario;
            }
        }
        System.out.println("Funcionário não encontrado.");
        return null;
    }
    
    /**
     * Altera os dados de um funcionário
     * @param funcionario Funcionário a ser alterado
     * @param novoNome Nome atualizado
     * @param novaQualificacao Qualificação atualizada
     * @param novaDescricaoFuncao Descrição atualizada
     * @param novaCargaHoraria Carga horária atualizada
     */
    public static void alterar(Funcionario funcionario, String novoNome, String novaQualificacao, String novaDescricaoFuncao, int novaCargaHoraria) {
        funcionario.setNome(novoNome);
        funcionario.setQualificacao(novaQualificacao);
        funcionario.setDescFuncao(novaDescricaoFuncao);
        funcionario.setCargaHoraria(novaCargaHoraria);
        DadosVendas.redirecionarReferencias(funcionario, funcionario);
    }
    
    /**
     * Remove um funcionário
     * @param funcionario Funcionário a ser removido
     */
    public static void remover(Funcionario funcionario) {
        if(funcionarios.contains(funcionario)) {
            DadosVendas.redirecionarReferencias(funcionario, funcionario.clone());
            funcionarios.remove(funcionario);
            System.out.println("Funcionário removido com sucesso!");
        }
        else
            System.out.println("Funcionário não encontrado!");
    }
    
    /**
     * Verifica se não existe nenhum funcionário cadastrado
     * @return {@code true} se não hover nenhum funionário cadastrado.
     */
    public static boolean semCadastros() {
        return funcionarios.isEmpty();
    }
    
    /**
     * Verifica se uma determinada matrícula está cadastrada
     * @param matricula Matrícula do funcionário
     * @return {@code true} se houver algum funcionário com essa matrícula.
     */
    public static boolean matriculaExiste(int matricula) {
        for(Funcionario funcionario : funcionarios) {
            if(funcionario.getNumeroMatricula() == matricula)
                return true;
        }
        return false;
    }
}
