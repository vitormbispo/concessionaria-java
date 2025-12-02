package trabalhopoo1.excecoes;

/**
 * Exceção para entradas de dados inválidas.
 * @author vitorbispo
 */
public class EntradaInvalidaException extends RuntimeException{

    public EntradaInvalidaException(String message) {
        super(message);
    }
    
}
