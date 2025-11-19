package trabalhopoo1.dados;

import trabalhopoo1.entidades.Cliente;
import java.util.ArrayList;

/**
 * Classe para armazenar e gerenciar os clientes
 */
public class DadosClientes {
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }
        
    /**
     * Cadastra um novo cliente.
     * @param cliente Cliente a ser cadastrado
     */
    public static void cadastrar(Cliente cliente) {
        clientes.add(cliente);
        DadosVendas.redirecionarReferencias(cliente, cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    /**
     * Procura por um cliente a partir do seu CPF
     * @param cpf CPF do cliente a consultar
     * @return O objeto do cliente consultado ou {@code null} caso não seja encontrado.
     */
    public static Cliente consultar(String cpf) {
        for(Cliente cliente : clientes) {
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        
        System.out.println("/!\\Cliente não encontrado!");
        return null;
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
        DadosVendas.redirecionarReferencias(cliente, cliente);
    }
    
    /**
     * Remove um cliente.
     * @param cliente Cliente a ser removido
     */
    public static void remover(Cliente cliente) {
        if(clientes.contains(cliente)) {
            DadosVendas.redirecionarReferencias(cliente,cliente.clone());
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso.");
        } else
            System.out.println("Cliente não encontrado!.");
        
    }
    
    /**
     * Lista todos os clientes cadastrados.
     */
    public static void listar() {
        System.out.println("\n================== Clientes ==================\n");
        if(semCadastros()) {
            System.out.println("-- Nenhum cliente cadastrado --");
            return;
        }
        
        for(int i = 0; i < clientes.size(); i++) {
            System.out.printf("%s - %s\n",i+1,clientes.get(i).toString());
        }
    }
    
    /**
     * Verifica se não existe nenhum cliente cadastrado
     * @return {@code true} se não hover nenhum cliente cadastrado.
     */
    public static boolean semCadastros() {
        return clientes.isEmpty();
    }
    
    // Validações
    
    /**
     * Valida se o telefone contém apenas números
     * @param telefone Telefone a validar
     * @return {@code true} se o telefone for válido.
     */
    public static boolean validarTelefone(String telefone) {
        boolean valido = false;
        
        if(!telefone.matches("\\d+")) {
            System.out.println("O telefone deve conter apenas números!");
        } else
            valido = true;
        
        return valido;
    }
    
    /** 
     * Verifica a validade do Email baseado nos fatores: <br>
     *  - Deve conter um @; <br>
     *  - O Email não pode estar cadastrado.
     * @param email Email a validar
     * @return {@code true} se o email for válido.
     */
    public static boolean validarEmail(String email) {
        boolean valido = false;
        
        if(emailExiste(email)) {
            System.out.println("Esse e-mail já está cadastrado!");
        } else if (!email.contains("@") || email.length() < 5){
            System.out.println("E-mail inválido!");
        }else
            valido = true;
        
        return valido;
    }
    
    /**
     * Verifica a validade do CPF baseado nos fatores: <br>
     *  - Tamanho deve ser 11; <br>
     *  - Deve conter apenas números; <br>
     *  - O CPF não pode estar cadastrado.
     * @param cpf CPF a validar
     * @return {@code true} se o CPF for válido.
     */
    public static boolean validarCpf(String cpf) {
        boolean valido = false;
        
        if(cpf.length() != 11) {
            System.out.println("Tamanho do CPF incorreto!");
        } else if (!cpf.matches("\\d+")) {
            System.out.println("O CPF deve conter apenas números!");
        } else if (cpfExiste(cpf)){
            System.out.println("Esse CPF já está cadastrado!");
        } else
            valido = true;
        
        return valido;
    }
    
        /**
     * Verifica a validade do RG baseado nos fatores: <br>
     *  - Deve conter apenas números; <br>
     *  - O RG não pode estar cadastrado.
     * @param rg RG a validar
     * @return {@code true} se o RG for válido.
     */
    public static boolean validarRg(String rg) {
        boolean valido = false;
        
        if (!rg.matches("\\d+")) {
            System.out.println("O RG deve conter apenas números!");
        } else if (rgExiste(rg)){
            System.out.println("Esse RG já está cadastrado!");
        } else 
            valido = true;
        
        return valido;
    }
    
    /**
     * Verifica se o CPF já está em uso
     * @param cpf CPF
     * @return {@code true} se o CPF já está cadastrado.
     */
    public static boolean cpfExiste(String cpf) {
       for(int i = 0; i < clientes.size();i++) {
           if(clientes.get(i).getCpf().equals(cpf))
               return true;
       }
       return false;
    }
    
    /**
     * Verifica se o RG já está em uso
     * @param rg RG
     * @return {@code true} se o RG já está cadastrado.
     */
    public static boolean rgExiste(String rg) {
       for(int i = 0; i < clientes.size();i++) {
           if(clientes.get(i).getRg().equals(rg))
               return true;
       }
       return false;
    }
    
     /**
     * Verifica se o email já está em uso
     * @param email email
     * @return {@code true} se o email já está cadastrado.
     */
    public static boolean emailExiste(String email) {
       for(int i = 0; i < clientes.size();i++) {
           if(clientes.get(i).getEmail().equals(email))
               return true;
       }
       return false;
    }
}
