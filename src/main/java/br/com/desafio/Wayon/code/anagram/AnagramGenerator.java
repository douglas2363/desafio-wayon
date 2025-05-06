package br.com.desafio.Wayon.code.anagram;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável por gerar todos os possíveis anagramas de uma string de entrada.
 * Um anagrama é uma reorganização das letras de uma palavra para formar novas combinações.
 */

public class AnagramGenerator {

    /**
     * Gera todos os anagramas possíveis para a string de entrada.
     *
     * @param input String contendo apenas caracteres alfabéticos (a-z, A-Z)
     * @return Lista com todos os anagramas possíveis
     * @throws IllegalArgumentException Se:
     *         - input for null
     *         - input for vazio ("")
     *         - input contiver caracteres não alfabéticos
     */

    public static List<String> generateAnagrams(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }
        if (!input.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Input string must contain only letters");
        }

        // Caso base: uma única letra → retorno direto
        if (input.length() == 1) {
            List<String> base = new ArrayList<>();
            base.add(input);
            return base;
        }

        // Caso recursivo
        List<String> anagrams = new ArrayList<>();
        char firstChar = input.charAt(0);
        String remainingChars = input.substring(1);

        for (String subAnagram : generateAnagrams(remainingChars)) {
            for (int i = 0; i <= subAnagram.length(); i++) {
                String anagram = subAnagram.substring(0, i) + firstChar + subAnagram.substring(i);
                anagrams.add(anagram);
            }
        }

        return anagrams;
    }
}
