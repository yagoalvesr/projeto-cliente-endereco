package br.com.pan.infrastructure.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Util {
    /**
     * @param lista         a lista a ser ordenada
     * @param extratorChave função que extrai a chave de comparação
     * @param <T>           tipo dos elementos da lista
     * @param <U>           tipo da chave de ordenação (deve ser Comparable)
     */
    public static <T, U extends Comparable<? super U>> void ordenarPor(List<T> lista, Function<T, U> extratorChave) {
        lista.sort(Comparator.comparing(extratorChave));
    }

    /**
     * Remove todos os caracteres que não são dígitos de uma string.
     *
     * @param input string original
     * @return string contendo apenas números
     */
    public static String apenasNumeros(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[^\\d]", "");
    }

}
