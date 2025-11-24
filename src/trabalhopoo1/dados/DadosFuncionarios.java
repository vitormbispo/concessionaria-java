package trabalhopoo1.dados;

import trabalhopoo1.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;
import trabalhopoo1.excecoes.EntradaInvalidaException;

public class DadosFuncionarios {
    private static List<Funcionario> funcionarios = new ArrayList<>();

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
    public static void alterar(Funcionario funcionario, String novoNome, long novaMatricula, String novaQualificacao, String novaDescricaoFuncao, int novaCargaHoraria) {
        funcionario.setNome(novoNome);
        funcionario.setMatricula(novaMatricula);
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
        }
    }
    
    /**
     * Remove todos os funcionários da lista.
     */
    public static void removerTodos() {
        funcionarios = new ArrayList();
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
    public static boolean matriculaExiste(long matricula) {
        for(Funcionario funcionario : funcionarios) {
            if(funcionario.getNumeroMatricula() == matricula)
                return true;
        }
        return false;
    }
    
    /**
     * Valida uma entrada de matrícula de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - Deve ser única
     * @param matricula Nº de matrícula (como texto)
     * @return 
     */
    public static boolean validarMatricula(String matricula) {
        boolean valido = false;
        
        if(matricula.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else{
            try {
                long convertido = Long.parseLong(matricula);
                if(matriculaExiste(convertido))
                    throw new EntradaInvalidaException("Esse Nº de matrícula já está cadastrado!");
                else
                    valido = true;
            } catch(NumberFormatException e) {
                throw new EntradaInvalidaException("Deve conter apenas números!");
            }
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de carga horária de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - Deve estar entre 1 e 50 horas semanais
     * @param carga Nº de matrícula (como texto)
     * @return 
     */
    public static boolean validarCargaHoraria(String carga) {
        boolean valido = false;
        
        if(carga.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else{
            try {
                int convertido = Integer.parseInt(carga);
                if(convertido < 1 || convertido > 50)
                    throw new EntradaInvalidaException("Carga horária inválida! (1-50)");
                else
                    valido = true;
            } catch(NumberFormatException e) {
                throw new EntradaInvalidaException("Deve conter apenas números!");
            }
        }
        
        return valido;
    }
}
