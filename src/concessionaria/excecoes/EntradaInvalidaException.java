package concessionaria.excecoes;

/**
 * Exceção para entradas de dados inválidas.
 */
public class EntradaInvalidaException extends RuntimeException{
    public EntradaInvalidaException(String message) {
        super(message);
    }
}
