package concessionaria.dados;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import concessionaria.entidades.Funcionario;
import static concessionaria.dados.BancoDados.em;
import concessionaria.excecoes.EntradaInvalidaException;

public class DadosFuncionarios {
    /**
     * Cadastra um novo funcionario.
     * @param funcionario Funcionario a ser cadastrado
     */
    public static void cadastrar(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }
    
    /**
     * Procura por todos os funcionários cadastrados
     * @return Uma lista com todos os funcionários.
     */
    public static List<Funcionario> consultarTodos() {
        Query query = em.createQuery("SELECT f FROM Funcionario f");
        List<Funcionario> resultado = query.getResultList();
        return resultado;
    }
    
    /**
     * Procura por um funcionário a partir do seu ID
     * @param id ID do funcionário a consultar
     * @return O objeto do funcionário consultado ou {@code null} caso não seja encontrado.
     */
    public static Funcionario consultarId(String id) {
        Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.id LIKE :idFuncionario");
        query.setParameter("idFuncionario", id);
        
        try {
            Funcionario resultado = (Funcionario) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Procura por um funcionário a partir do seu número de matrícula
     * @param matricula Nº dr matrrícula do funcionário a consultar
     * @return O objeto do funcionário consultado ou {@code null} caso não seja encontrado.
     */
    public static Funcionario consultarMatricula(long matricula) {
        Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.numeroMatricula LIKE :matriculaFuncionario");
        query.setParameter("matriculaFuncionario", Long.toString(matricula));
        
        try {
            Funcionario resultado = (Funcionario) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Procura funcionários a partir do seu nome
     * @param nome Nome do funcionário a consultar
     * @return Uma lista de funcionários encontrados pelo nome.
     */
    public static List<Funcionario> consultarNome(String nome) {
        Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE :nomeFuncionario");
        query.setParameter("nomeFuncionario", "%"+nome+"%");
        
        List<Funcionario> resultado = query.getResultList();
        return resultado;
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
        
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }
    
    /**
     * Remove um funcionário
     * @param funcionario Funcionário a ser removido
     */
    public static void remover(Funcionario funcionario) {
        DadosVendas.removerRefFuncionario(funcionario);
        em.getTransaction().begin();
        em.remove(funcionario);
        em.getTransaction().commit();
    }
    
    /**
     * Remove todos os funcionários cadastrados.
     */
    public static void removerTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Funcionario").executeUpdate();
        em.getTransaction().commit();
    }
    
    /**
     * Verifica se não existe nenhum funcionário cadastrado
     * @return {@code true} se não hover nenhum funionário cadastrado.
     */
    public static boolean semCadastros() {
        Query query = em.createQuery("SELECT COUNT(f) FROM Funcionario f");
        boolean vazio = (long)query.getSingleResult() == 0;
        return vazio;
    }
    
    /**
     * Verifica se uma determinada matrícula está cadastrada
     * @param matricula Matrícula do funcionário
     * @return {@code true} se houver algum funcionário com essa matrícula.
     */
    public static boolean matriculaExiste(long matricula) {
        Query query = em.createQuery("SELECT COUNT(f) FROM Funcionario f WHERE f.numeroMatricula = :nMatricula");
        query.setParameter("nMatricula", matricula);
        boolean existe = (long)query.getSingleResult() != 0;
        return existe;
    }
    
    /**
     * Valida uma entrada de matrícula de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - Deve ser única
     * @param matricula Nº de matrícula (como texto)
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarMatricula(String matricula) {
        boolean valido = false;
        
        if(matricula.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(matricula.length() > 20)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (20)");
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
     * @param carga Carga horária (como texto)
     * @return {@code true} se a entrada for válida
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
    
     /**
     * Valida uma entrada de qualificação de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    -  Não deve ultrapassar 50 caracteres.
     * @param qualificacao Qualificação
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarQualificacao(String qualificacao) {
        boolean valido = false;
        if(qualificacao.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if (qualificacao.length() > 50)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (50)");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de descrição de função de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Não deve ultrapassar 200 caracteres.
     * @param descFuncao Descrição da função
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarDescFuncao(String descFuncao) {
        boolean valido = false;
        if(descFuncao.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if (descFuncao.length() > 200)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (200)");
        else
            valido = true;
        
        return valido;
    }
    
     /**
     * Valida uma entrada de qualificação de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Não deve ter mais de 100 caracteres.
     * @param nome Nome do funcionário
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarNome(String nome) {
        boolean valido = false;
        if(nome.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if (nome.length() > 100)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (100)");
        else
            valido = true;
        
        return valido;
    }
}
