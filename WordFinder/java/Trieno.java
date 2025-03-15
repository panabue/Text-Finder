import java.util.*;

public class Trieno {
    Map<Character, Trieno> children;
    boolean isEndOfWord;
    List<String> documents; // Lista de documentos onde a palavra aparece

    public Trieno() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.documents = new ArrayList<>();
    }
}