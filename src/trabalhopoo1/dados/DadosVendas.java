package trabalhopoo1.dados;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import trabalhopoo1.entidades.Funcionario;
import trabalhopoo1.entidades.Venda;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.entidades.Cliente;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import static trabalhopoo1.dados.BancoDados.em;
import trabalhopoo1.excecoes.EntradaInvalidaException;

public class DadosVendas {
    /**
     * Cadastra uma nova venda
     * @param venda Venda a cadastrar
     */
    public static void cadastrar(Venda venda) {
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
    }
    
    /**
     * Consulta todas as vendas cadastradas
     * @return Uma lista com todas as vendas cadastradas
     */
    public static List<Venda> consultarTodas() {
        Query query = em.createQuery("SELECT v from Venda v");
        List<Venda> resultado = query.getResultList();
        return resultado;
    }
    
    /**
     * Consulta pela venda com determinado ID
     * @param id ID da venda
     * @return Venda com o ID
     */
    public static Venda consultarId(String id) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.id LIKE :idVenda");
        query.setParameter("idVenda", id);
        try {
            Venda resultado = (Venda) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
        
    }
    /**
     * Consulta por vendas através do CPF do cliente
     * @param cpfCliente CPF do cliente
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarCPF(String cpfCliente) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.cliente.cpf = :cpfCliente");
        query.setParameter("cpfCliente", cpfCliente);
        List<Venda> resultado = query.getResultList();
        return resultado;
    }
    
    /**
     * Consulta por vendas através do nome do cliente
     * @param nome Nome do cliente
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarNomeCliente(String nome) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.cliente.nome LIKE :nomeCliente");
        query.setParameter("nomeCliente", "%"+nome+"%");
        List<Venda> resultado = query.getResultList();
        return resultado;
    }
    
    /**
     * Consulta por vendas através do número de matrícula do funcionário
     * @param numMatricula Nº de matrícula do funcionário
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarNMatricula(String numMatricula) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.funcionario.numeroMatricula LIKE :numMatricula");
        query.setParameter("numMatricula", numMatricula);
        return query.getResultList();
    }
    
    /**
     * Consulta por vendas através do nome do funcionário
     * @param nome Nome do funcionário
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarNomeFuncionario(String nome) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.funcionario.nome LIKE :nomeFuncionario");
        query.setParameter("nomeFuncionario", "%"+nome+"%");
        return query.getResultList();
    }
    
    /**
     * Consulta por vendas através do chassi do veículo
     * @param chassi Chassi do veículo
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarChassi(String chassi) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.veiculo.chassi = :chassiVeiculo");
        query.setParameter("chassiVeiculo", chassi);
        return query.getResultList();
    }
    
    /**
     * Consulta por vendas através do modelo do veículo
     * @param modelo Modelo do veículo
     * @return Lista com as vendas encontradas
     */
    public static List<Venda> consultarModeloVeiculo(String modelo) {
        Query query = em.createQuery("SELECT v from Venda v WHERE v.veiculo.modelo LIKE :modeloVeiculo");
        query.setParameter("modeloVeiculo", "%"+modelo+"%");
        return query.getResultList();
    }
    
    /**
     * Altera os dados de uma venda
     * @param venda Venda atualizada
     * @param data Data atualizada
     * @param novoValor Valor atualizado
     * @param novoCliente Cliente atualizado
     * @param novoFuncionario Funcionario atualizado
     * @param novoVeiculo Veiculo atualizado
     */
    public static void alterar(Venda venda, LocalDate data, double novoValor, Cliente novoCliente, Funcionario novoFuncionario, Veiculo novoVeiculo) {
        venda.setData(data);
        venda.setValor(novoValor);
        venda.setCliente(novoCliente);
        venda.setFuncionario(novoFuncionario);
        venda.setVeiculo(novoVeiculo);
        
        em.getTransaction().begin();
        em.merge(venda);
        em.getTransaction().commit();
    }

    /**
     * Remove a venda
     * @param venda Venda a ser removida
     */
    public static void remover(Venda venda) {
        em.getTransaction().begin();
        em.remove(venda);
        em.getTransaction().commit();
    }
    
    /**
     * Remove todas as vendas cadastradas
     */
    public static void removerTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Venda").executeUpdate();
        em.getTransaction().commit();
    }
    
     /**
     * Verifica se não há cadastros de vendas
     * @return {@code true} se nenhuma venda foi cadastrada.
     */
    public static boolean semCadastros() {
        Query query = em.createQuery("SELECT COUNT(v) FROM Venda v");
        boolean vazio = (long)query.getSingleResult() == 0;
        return vazio;
    }
    
    // Validações
    
    /**
     * Valida uma entrada de CPF de cliente de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Cliente com o CPF deve existir
     * @param cpf CPF
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarCliente(String cpf) {
        boolean valido = false;
        
        if(cpf.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(!DadosClientes.cpfExiste(cpf))
            throw new EntradaInvalidaException("Nenhum cliente com esse CPF!");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de número de matrícula de funcionário de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Funcionárico com o Nº de matrícula deve existir
     * @param numMatricula Nº de matrícula
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarFuncionario(String numMatricula) {
        boolean valido = false;
        
        if(numMatricula.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try{
            long convertido = Long.parseLong(numMatricula);
            if(!DadosFuncionarios.matriculaExiste(convertido))
                throw new EntradaInvalidaException("Nenhum funcionário com essa matrícula!");
            else
                valido = true;
        } catch(NumberFormatException e) {
            throw new EntradaInvalidaException("Nº de matrícula inválido!");
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de chassi de veículo de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Veículo com o chassi deve existir
     * @param chassi Chassi
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarVeiculo(String chassi) {
        boolean valido = false;
        
        if(chassi.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(!DadosVeiculos.chassiExiste(chassi))
            throw new EntradaInvalidaException("Nenhum veículo com esse chassi!");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de data de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve seguir o formato AAAA-MM-DD
     * @param data Data
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarData(String data) {
        boolean valido = false;
        
        if(data.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            if(LocalDate.parse(data) != null)
                valido = true;
        } catch (DateTimeParseException e) {
            throw new EntradaInvalidaException("Formato inválido! (AAAA-MM-DD)");
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de valor de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve ser número válido
     * @param valor Valor
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarValor(String valor) {
        boolean valido = false;
        
        if(valor.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            double convertido = Double.parseDouble(valor);
            if(convertido < 0)
                throw new EntradaInvalidaException("Valor inválido!");
            else
                valido = true;
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Deve ser um número! (Ex.: 12.34)");
        }
        
        return valido;
    }
}
