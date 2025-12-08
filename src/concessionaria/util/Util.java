package concessionaria.util;

public class Util {
    /**
     * Abrevia um texto em uma quantidade máxima de caracteres
     * @param texto Texto a abreviar
     * @param max Máximo de caracteres
     * @return Texto abreviado
     */
    public static String abreviar(String texto, int max) {
        if(texto.length() < max)
            return texto;
        return texto.substring(0, max-3).concat("...");
    }
}
