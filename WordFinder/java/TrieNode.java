public class TrieNode implements Comparable<TrieNode> {
  char character; // Caractere armazenado no no
  int frequency; // frequencia do caractere
  TrieNode left, right; // Filhos a esquerda e direita

  public TrieNode(int frequency, TrieNode left, TrieNode right) {
    this.character = '\0';
    this.frequency = frequency;
    this.left = left;
    this.right = right;
  }

  public TrieNode(char character, int frequency) {
    this.character = character;
    this.frequency = frequency;
    this.left = null;
    this.right = null;
  }

  @Override
  public int compareTo(TrieNode o) {
    return this.frequency - o.frequency;
  }

}
