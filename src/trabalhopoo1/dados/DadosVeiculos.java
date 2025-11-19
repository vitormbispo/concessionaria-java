package trabalhopoo1.dados;

import trabalhopoo1.entidades.Veiculo;
import java.util.ArrayList;
import java.util.List;

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
            if (veiculo.getNome().equalsIgnoreCase(nome) || veiculo.getChassi().equalsIgnoreCase(nome)) {
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
     * @param novoNome Nome atualizado
     * @param novaCor Cor atualizada
     * @param novoNumMarchas Número de marchas atualizada
     * @param novoNumPortas Número de portas atualizada
     * @param novaMarca Marca atualizada
     * @param novoAno Ano atualizado
     */
    public static void alterar(Veiculo veiculo, String novoNome, String novaCor, int novoNumMarchas, int novoNumPortas, String novaMarca, int novoAno) {
        veiculo.setNome(novoNome);
        veiculo.setCor(novaCor);
        veiculo.setNumMarchas(novoNumMarchas);
        veiculo.setNumPortas(novoNumPortas);
        veiculo.setMarca(novaMarca);
        veiculo.setAno(novoAno);
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
    
    /**
     * Lista todos os veículos
     */
    public static void listar() {
        System.out.println("\n================== Veículos ==================\n");
        if(veiculos.size() == 0) {
            System.out.println("-- Nenhum veículo cadastrado --");
            return;
        }
        
        for(int i = 0; i < veiculos.size(); i++) {
            System.out.printf("%s - %s\n",i+1,veiculos.get(i).toString());
        }
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
     * @param veiculo
     * @return {@code true} se existe um veículo com esse nome
     */
    public static boolean veiculoExiste(String nome) {
        for(Veiculo veiculo : veiculos) {
            if(veiculo.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }
}
