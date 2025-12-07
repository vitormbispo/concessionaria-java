package trabalhopoo1.dados;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import static trabalhopoo1.dados.BancoDados.em;
import trabalhopoo1.entidades.Veiculo;
import trabalhopoo1.excecoes.EntradaInvalidaException;

public class DadosVeiculos {
    /**
     * Cadastra um novo veículo
     * @param veiculo 
     */
    public static void cadastrar(Veiculo veiculo) {
        em.getTransaction().begin();
        em.persist(veiculo);
        em.getTransaction().commit();
    }
    
    /**
     * Procura por todos os veículos cadastrados
     * @return Uma lista com todos os veículos.
     */
    public static List<Veiculo> consultarTodos() {
        Query query = em.createQuery("SELECT v FROM Veiculo v");
        List<Veiculo> resultado = query.getResultList();
        return resultado;
    }
    
    /**
     * Procura por um veículo a partir do seu ID
     * @param id ID do veículo a consultar
     * @return O objeto do veículo consultado ou {@code null} caso não seja encontrado.
     */
    public static Veiculo consultarId(String id) {
        Query query = em.createQuery("SELECT v FROM Veiculo v WHERE v.id LIKE :idVeiculo");
        query.setParameter("idVeiculo", id);
        
        try {
            Veiculo resultado = (Veiculo) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Consulta por um veículo a partir do seu chassi
     * @param chassi Chassi do veículo
     * @return Lista de cadastros encontrados
     */
    public static Veiculo consultarChassi(String chassi) {  
        Query query = em.createQuery("SELECT v FROM Veiculo v WHERE v.chassi LIKE :chassiVeiculo");
        query.setParameter("chassiVeiculo", chassi);
        
        try {
            Veiculo resultado = (Veiculo) query.getSingleResult();
            return resultado;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    /**
     * Consulta por veículos a partir do seu modelo
     * @param modelo Modelo do veículo
     * @return Lista de cadastros encontrados
     */
    public static List<Veiculo> consultarModelo(String modelo) {  
        Query query = em.createQuery("SELECT v FROM Veiculo v WHERE v.modelo LIKE :modeloVeiculo");
        query.setParameter("modeloVeiculo", "%"+modelo+"%");
        return query.getResultList();
    }
    
    /**
     * Consulta por veículos a partir da sua marca
     * @param marca Modelo do veículo
     * @return Lista de cadastros encontrados
     */
    public static List<Veiculo> consultarMarca(String marca) {  
        Query query = em.createQuery("SELECT v FROM Veiculo v WHERE v.marca LIKE :modeloVeiculo");
        query.setParameter("modeloVeiculo", "%"+marca+"%");
        return query.getResultList();
    }
    
    /**
     * Altera os dados de um veículo
     * @param veiculo Veiculo
     * @param novoModelo Nome atualizado
     * @param novaMarca Marca atualizada
     * @param novaCor Cor atualizada
     * @param novoAno Ano atualizado
     * @param novoNumMarchas Número de marchas atualizada
     * @param novoNumPortas Número de portas atualizada
     */
    public static void alterar(Veiculo veiculo, String novoModelo, String novaMarca, String novaCor, int novoAno, int novoNumMarchas, int novoNumPortas, String chassi) {
        veiculo.setModelo(novoModelo);
        veiculo.setCor(novaCor);
        veiculo.setNumMarchas(novoNumMarchas);
        veiculo.setNumPortas(novoNumPortas);
        veiculo.setMarca(novaMarca);
        veiculo.setAno(novoAno);
        veiculo.setChassi(chassi);
        
        em.getTransaction().begin();
        em.merge(veiculo);
        em.getTransaction().commit();
    }
    
    /**
     * Remove um veículo
     * @param veiculo Veículo a ser removido
     */
    public static void remover(Veiculo veiculo) {
        em.getTransaction().begin();
        em.remove(veiculo);
        em.getTransaction().commit();
    }
    
    /**
     * Remove todos os veículos cadastrados.
     */
    public static void removerTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Veiculo").executeUpdate();
        em.getTransaction().commit();
    }
    
    /**
     * Verifica se não existe nenhum veículo cadastrado
     * @return {@code true} se não hover nenhum veículo cadastrado.
     */
    public static boolean semCadastros() {
        Query query = em.createQuery("SELECT COUNT(v) FROM Veiculo v");
        boolean vazio = (long)query.getSingleResult() == 0;
        return vazio;
    }
    
    /**
     * Verifica se existe um veículo cadastrado com determinado chassi
     * @param chassi Nº do chassi a verificar
     * @return {@code true} se existir um veículo com esse chassi
     */
    public static boolean chassiExiste(String chassi) {
        Query query = em.createQuery("SELECT COUNT(v) FROM Veiculo v WHERE v.chassi = :chassiVeiculo");
        query.setParameter("chassiVeiculo", chassi);
        boolean vazio = (long)query.getSingleResult() != 0;
        return vazio;
    }
    
    // Validações
    
    /**
     * Valida uma entrada de modelo do veículo de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Não deve exceder 30 caracteres
     * @param modelo Modelo
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarModelo(String modelo) {
        boolean valido = false;
        
        if(modelo.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(modelo.length() > 30)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (30)");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de cor do veículo de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Não deve exceder 20 caracteres
     * @param cor Cor
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarCor(String cor) {
        boolean valido = false;
        
        if(cor.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(cor.length() > 20)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (20)");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de marca do veículo de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Não deve exceder 30 caracteres
     * @param marca Marca
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarMarca(String marca) {
        boolean valido = false;
        
        if(marca.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(marca.length() > 30)
            throw new EntradaInvalidaException("Limite de caracteres atingido! (30)");
        else
            valido = true;
        
        return valido;
    }
    
    /**
     * Valida uma entrada de ano de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - O ano não pode ser inferior a 1900
     * @param ano Ano (como texto)
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarAno(String ano) {
        boolean valido = false;
        
        if(ano.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            int convertido = Integer.parseInt(ano);
            if(convertido < 1900)
                throw new EntradaInvalidaException("O ano deve ser válido! (> 1900)");
            else
                valido = true;
        } catch(NumberFormatException e) {
            throw new EntradaInvalidaException("Deve ser um número válido!");
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de número de marchas de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - O Nº de marchas deve estar entre 1 e 10
     * @param numMarchas Nº de marchas (como texto)
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarNumMarchas(String numMarchas) {
        boolean valido = false;
        
        if(numMarchas.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            int convertido = Integer.parseInt(numMarchas);
            if(convertido < 1 || convertido > 10)
                throw new EntradaInvalidaException("O Nº de marchas deve ser válido! (1-10)");
            else
                valido = true;
        } catch(NumberFormatException e) {
            throw new EntradaInvalidaException("Deve ser um número válido!");
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de número de portas de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve conter apenas números <br>
     *    - O número de portas deve estar entre 1 e 5
     * @param numPortas Número de portas (como texto)
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarNumPortas(String numPortas) {
        boolean valido = false;
        
        if(numPortas.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        try {
            int convertido = Integer.parseInt(numPortas);
            if(convertido < 1 || convertido > 5)
                throw new EntradaInvalidaException("O Nº de portas deve ser válido! (1-5)");
            else
                valido = true;
        } catch(NumberFormatException e) {
            throw new EntradaInvalidaException("Deve ser um número válido!");
        }
        
        return valido;
    }
    
    /**
     * Valida uma entrada de chassi de acordo com os seguintes fatores: <br>
     *    - Não pode ser vazia <br>
     *    - Deve ser único
     * @param chassi Chassi
     * @return {@code true} se a entrada for válida
     */
    public static boolean validarChassi(String chassi) {
        boolean valido = false;
        
        if(chassi.isBlank())
            throw new EntradaInvalidaException("O campo não pode estar vazio!");
        else if(chassiExiste(chassi))
            throw new EntradaInvalidaException("Esse chassi já foi registrado!");
        else
            valido = true;
        
        return valido;
    }
}
