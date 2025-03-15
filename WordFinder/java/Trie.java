import java.util.*;

public class Trie {
    private final Trieno root;

    public Trie() {
        root = new Trieno();
    }

    // Limpa as palavras (remover pontuações)
    private String cleanWord(String word) {
        return word.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚçÇãõÃÕ]", "").toLowerCase();
    }

    // Insere uma frase na Trie
    public void insert(String phrase) {
        // Divide a frase em palavras e insere cada palavra limpa na Trie
        String[] words = phrase.split("\\s+");
        for (String word : words) {
            String clean = cleanWord(word);
            if (!clean.isEmpty()) {
                Trieno node = root;
                for (char ch : clean.toCharArray()) {
                    node.children.putIfAbsent(ch, new Trieno());
                    node = node.children.get(ch);
                }
                node.isEndOfWord = true;
            }
        }
    }

    // Verifica se uma palavra limpa existe na Trie
    public boolean searchWord(String word) {
        String clean = cleanWord(word);
        Trieno node = root;
        for (char ch : clean.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }
}