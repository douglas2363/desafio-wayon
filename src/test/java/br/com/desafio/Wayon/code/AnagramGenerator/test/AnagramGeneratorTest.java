package br.com.desafio.Wayon.code.AnagramGenerator.test;

import br.com.desafio.Wayon.code.anagram.AnagramGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramGeneratorTest {

    /**
     * Teste básico com 3 letras distintas
     */
    @Test
    public void testAnagramsOfABC() {
        String input = "abc";
        List<String> expectedAnagrams = List.of("abc", "acb", "bac", "bca", "cab", "cba");
        List<String> actualAnagrams = AnagramGenerator.generateAnagrams(input);
        assertEquals(expectedAnagrams.size(), actualAnagrams.size());
        assertTrue(actualAnagrams.containsAll(expectedAnagrams));
    }

    /**
     * Teste com letras repetidas - espera-se que haja duplicatas, pois a implementação atual não remove
     */
    @Test
    public void testAnagramsWithRepeatedLetters() {
        String input = "aab";
        List<String> result = AnagramGenerator.generateAnagrams(input);

        // Deve gerar 6 combinações (3! = 6), mas algumas podem ser iguais
        assertEquals(6, result.size());

        // Vamos usar um Set para verificar quantos são únicos
        Set<String> unique = new HashSet<>(result);
        assertTrue(unique.contains("aab"));
        assertTrue(unique.contains("aba"));
        assertTrue(unique.contains("baa"));
        assertEquals(3, unique.size()); // Deveriam haver apenas 3 únicos
    }

    /**
     * Teste com uma única letra (caso base)
     */
    @Test
    public void testSingleLetter() {
        String input = "x";
        List<String> result = AnagramGenerator.generateAnagrams(input);
        assertEquals(List.of("x"), result);
    }

    /**
     * Teste com letras maiúsculas
     */
    @Test
    public void testUppercaseLetters() {
        String input = "AB";
        List<String> result = AnagramGenerator.generateAnagrams(input);
        assertEquals(2, result.size());
        assertTrue(result.contains("AB"));
        assertTrue(result.contains("BA"));
    }

    /**
     * Teste com letras maiúsculas e minúsculas
     */
    @Test
    public void testMixedCaseLetters() {
        String input = "Ab";
        List<String> result = AnagramGenerator.generateAnagrams(input);
        assertEquals(2, result.size());
        assertTrue(result.contains("Ab"));
        assertTrue(result.contains("bA"));
    }

    /**
     * Teste com entrada vazia - deve lançar exceção
     */
    @Test
    public void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> AnagramGenerator.generateAnagrams(""));
    }

    /**
     * Teste com entrada nula - deve lançar exceção
     */
    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> AnagramGenerator.generateAnagrams(null));
    }

    /**
     * Teste com caracteres inválidos (não letras) - deve lançar exceção
     */
    @Test
    public void testNonLetterCharacters() {
        assertThrows(IllegalArgumentException.class, () -> AnagramGenerator.generateAnagrams("a1b"));
        assertThrows(IllegalArgumentException.class, () -> AnagramGenerator.generateAnagrams("a!b@"));
        assertThrows(IllegalArgumentException.class, () -> AnagramGenerator.generateAnagrams(" "));
    }

    /**
     * Teste com string maior (5 letras)
     */
    @Test
    public void testLongerInput() {
        String input = "earth";
        List<String> result = AnagramGenerator.generateAnagrams(input);

        // 5 letras distintas → 5! = 120 anagramas
        assertEquals(120, result.size());
        assertTrue(result.contains("earth"));
        assertTrue(result.contains("heart"));
    }
}
