package trabalhopoo1.dados;

import trabalhopoo1.entidades.Cliente;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import trabalhopoo1.excecoes.EntradaInvalidaException;
import static trabalhopoo1.dados.BancoDados.em;

/**
 * Classe para armazenar e gerenciar os clientes
 */
public class DadosClientes {  
    /**
     * Cadastra um novo cliente.
     * @param cliente Cliente a ser cadastrado
     */
    public static void cadastrar(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }
    
    
    /**
     * Procura por todos os clientes cadastrados
     * @return Uma lista com todos os clientes.
     */
    public static List<Cliente> consultarTodos() {
        Query query = em.createQuery("SELECT c FROM Cliente c");
        return query.getResultList();
    }
    
    /**
     * Procura por um cliente a partir do seu ID
     * @param id ID do cliente a consultar
     * @return O objeto do cliente consultado ou {@code null} caso não seja encontrado.
     */
    public static Cliente consultarId(long id) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.id LIKE :idCliente");
        query.setParameter("idCliente", Long.toString(id));
        
        try {
            Cliente resultado = (Cliente) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Procura por um cliente a partir do seu CPF
     * @param cpf CPF do cliente a consultar
     * @return O objeto do cliente consultado ou {@code null} caso não seja encontrado.
     */
    public static Cliente consultarCpf(String cpf) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf LIKE :cpfCliente");
        query.setParameter("cpfCliente", cpf);
        
        try {
            Cliente resultado = (Cliente) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Procura clientes a partir do seu nome
     * @param nome Nome do cliente a consultar
     * @return Uma lista de clientes encontrados pelo nome.
     */
    public static List<Cliente> consultarNome(String nome) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nomeCliente");
        query.setParameter("nomeCliente", "%"+nome+"%");
        
        List<Cliente> resultado = query.getResultList();
        return resultado;
    }
    
    
    /**
     * Altera os dados de um cliente
     * @param cliente Objeto do cliente
     * @param nome Novo nome
     * @param telefone Novo telefone
     * @param email Novo email
     * @param rg Novo RG
     * @param cpf Novo CPF
     */
    public static void alterar(Cliente cliente, String nome, String telefone, String email, String rg, String cpf) {
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setRg(rg);
        cliente.setCpf(cpf);
        
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }
    
    /**
     * Remove um cliente.
     * @param cliente Cliente a ser removido
     */
    public static void remover(Cliente cliente) {
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
    }
    
    /**
     * Remove todos os clientes
     */
    public static void removerTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Cliente").executeUpdate();
        em.getTransaction().commit();
    }
    
    /**
     * Verifica se não existe nenhum cliente cadastrado
     * @return {@code true} se não hover nenhum cliente cadastrado.
     */
    public static boolean semCadastros() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Cliente c");
        boolean vazio = (long)query.getSingleResult() == 0;
        return vazio;
    }
    
    // Validações
    
    /**
     * Verifica a validade do telefone baseado nos seguintes fatores: <br>
     * - Não pode ser vazio <br>
     * - Deve conter apenas números
     * @param telefone Telefone a validar
     * @return {@code true} se o telefone for válido.
     */
    public static boolean validarTelefone(String telefone) {
        boolean valido = false;
        
        if(telefone.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(!telefone.matches("\\d+"))
            throw new EntradaInvalidaException("O telefone deve conter apenas números!");
        else
            valido = true;
        
        return valido;
    }
    
    /** 
     * Verifica a validade do Email baseado nos fatores: <br>
     *  - Não pode ser vazio <br>
     *  - Deve conter um @; <br>
     *  - O Email não pode estar cadastrado.
     * @param email Email a validar
     * @return {@code true} se o email for válido.
     */
    public static boolean validarEmail(String email) {
        boolean valido = false;
        
        if(email.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(emailExiste(email))
           throw new EntradaInvalidaException("Esse e-mail já está cadastrado!");
        else if (!email.contains("@") || email.length() < 5)
            throw new EntradaInvalidaException("E-mail inválido! (Ex.: exemplo@email)");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Verifica a validade do CPF baseado nos fatores: <br>
     *  - Não pode ser vazio <br>
     *  - Tamanho deve ser 11; <br>
     *  - Deve conter apenas números; <br>
     *  - O CPF não pode estar cadastrado.
     * @param cpf CPF a validar
     * @return {@code true} se o CPF for válido.
     */
    public static boolean validarCpf(String cpf) {
        boolean valido = false;
        
        if(cpf.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(cpf.length() != 11)
            throw new EntradaInvalidaException("Tamanho do CPF incorreto!");
        else if (!cpf.matches("\\d+"))
            throw new EntradaInvalidaException("Deve conter apenas números!");
        else if (cpfExiste(cpf))
            throw new EntradaInvalidaException("Esse CPF já foi cadastrado!");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Verifica a validade do nome baseado nos fatores: <br>
     *  - Não pode ser vazio
     * @param nome Nome a validar
     * @return {@code true} se o nome for válido
     */
    public static boolean validarNome(String nome) {
        boolean valido = false;
        
        if(nome.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else
            valido = true;
        
        return valido;
    }
    
     /**
     * Verifica a validade do RG baseado nos fatores: <br>
     *  - Não pode ser vazio <br>
     *  - Deve conter apenas números; <br>
     *  - O RG não pode estar cadastrado.
     * 
     * @param rg RG a validar
     * @return {@code true} se o RG for válido.
     */
    public static boolean validarRg(String rg) {
        boolean valido = false;
        
        if (rg.isBlank()) 
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if (rgExiste(rg))
            throw new EntradaInvalidaException("Esse RG já está cadastrado!");
        else if (!rg.matches("\\d+")) 
            throw new EntradaInvalidaException("O RG deve conter apenas números!");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Verifica se o CPF já está em uso
     * @param cpf CPF
     * @return {@code true} se o CPF já está cadastrado.
     */
    public static boolean cpfExiste(String cpf) {
       Query query = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.cpf LIKE :cpfCliente");
       query.setParameter("cpfCliente",cpf);
       boolean existe = (long)query.getSingleResult() != 0;
       return existe;
    }
    
    /**
     * Verifica se o RG já está em uso
     * @param rg RG
     * @return {@code true} se o RG já está cadastrado.
     */
    public static boolean rgExiste(String rg) {
       Query query = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.rg LIKE :rgCliente");
       query.setParameter("rgCliente",rg);
       boolean existe = (long)query.getSingleResult() != 0;
       return existe;
    }
    
     /**
     * Verifica se o email já está em uso
     * @param email email
     * @return {@code true} se o email já está cadastrado.
     */
    public static boolean emailExiste(String email) {
       Query query = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.rg LIKE :emailCliente");
       query.setParameter("emailCliente",email);
       boolean existe = (long)query.getSingleResult() != 0;
       return existe;
    }
}
