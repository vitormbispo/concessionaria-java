package trabalhopoo1.dados;

import trabalhopoo1.entidades.Veiculo;
import java.util.ArrayList;
import java.util.List;
import trabalhopoo1.excecoes.EntradaInvalidaException;

public class DadosVeiculos {
    private static final List<Veiculo> veiculos = new ArrayList<>();

    public static List<Veiculo> getVeiculos() {
        return veiculos;
    }
    
    /**
     * Cadastra um novo veículo
     * @param veiculo 
     */
    public static void cadastrar(Veiculo veiculo) {
        if(veiculoExiste(veiculo)) {
            System.out.println("/!\\ Esse veículo já estava cadastrado!");
            return;
        }
        veiculos.add(veiculo);
        DadosVendas.redirecionarReferencias(veiculo, veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }
    
    /**
     * Consulta por um veículo a partir do seu nome ou chassi
     * @param nome Nome ou chassi do veículo
     * @return Lista de cadastros encontrados
     */
    public static ArrayList<Veiculo> consultar(String nome) {  
        ArrayList<Veiculo> encontrados = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getModelo().equalsIgnoreCase(nome) || veiculo.getChassi().equalsIgnoreCase(nome)) {
                encontrados.add(veiculo);
            }
        }
        if(encontrados.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
        }

        return encontrados;
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
     * 
     * 
     */
    public static void alterar(Veiculo veiculo, String novoModelo, String novaMarca, String novaCor, int novoAno, int novoNumMarchas, int novoNumPortas, String chassi) {
        veiculo.setModelo(novoModelo);
        veiculo.setCor(novaCor);
        veiculo.setNumMarchas(novoNumMarchas);
        veiculo.setNumPortas(novoNumPortas);
        veiculo.setMarca(novaMarca);
        veiculo.setAno(novoAno);
        veiculo.setChassi(chassi);
        DadosVendas.redirecionarReferencias(veiculo, veiculo);
    }
    
    /**
     * Remove um veículo
     * @param veiculo Veículo a ser removido
     */
    public static void remover(Veiculo veiculo) {
        if(veiculos.contains(veiculo)) {
            DadosVendas.redirecionarReferencias(veiculo, veiculo.clone());
            veiculos.remove(veiculo);
            System.out.println("Veículo removido com sucesso!");
        } else
            System.out.println("Veículo não encontrado!");
        
    }
    
    public static void removerTodos() {
        veiculos.removeAll(veiculos);
    }
    
    /**
     * Verifica se não existe nenhum veículo cadastrado
     * @return {@code true} se não hover nenhum veículo cadastrado.
     */
    public static boolean semCadastros() {
        return veiculos.isEmpty();
    }
    
    /**
     * Verifica se existe um veículo cadastrado com determinado chassi
     * @param chassi Nº do chassi a verificar
     * @return {@code true} se existir um veículo com esse chassi
     */
    public static boolean chassiExiste(String chassi) {
        for(Veiculo veiculo : veiculos) {
                if(veiculo.getChassi().equalsIgnoreCase(chassi)) {
                    return true;
                }
            }
            return false;
    }
    
    /**
     * Verifica se determinado veículo está cadastrado
     * @param veiculo
     * @return {@code true} se o veículo estiver cadastrado
     */
    public static boolean veiculoExiste(Veiculo veiculo) {
        for(Veiculo v : veiculos) {
            if(veiculo.equals(v))
                return true;
        }
        return false;
    }
    
    /**
     * Verifica se existe um veículo cadastrado com determinado nome
     * @param nome Nome do veículo
     * @return {@code true} se existe um veículo com esse nome
     */
    public static boolean veiculoExiste(String nome) {
        for(Veiculo veiculo : veiculos) {
            if(veiculo.getModelo().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }
    
    // Validações
    
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
