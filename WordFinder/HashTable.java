import java.util.LinkedList;

public class HashTable<K, V, N> {
  private LinkedList<HashEntry<K, V, N>>[] hashTable; // Tabela hash
  public int size; // Tamanho da tabela hash
  private String hashFunction; // Função de hash escolhida: "divisao" ou "djb2"

  @SuppressWarnings("unchecked")
  public HashTable(int size, String hashFunction) {
    hashTable = (LinkedList<HashEntry<K, V, N>>[]) new LinkedList[size]; // Casting explícito
    this.size = size;
    this.hashFunction = hashFunction.toLowerCase();
  }

  // Função de hash por divisão
  public int hashDivisao(String texto) {
    int soma = 0;
    for (char c : texto.toCharArray()) {
      soma += (int) c;
    }
    return soma % size;
  }

  // Função de hash DJB2
  public int hashDJB2(String texto) {
    long hash = 5381;
    for (char c : texto.toCharArray()) {
      hash = ((hash << 5) + hash) + c; // hash * 33 + c
    }
    return (int) (hash % size);
  }

  // Retorna a posição calculada com base na função de hash escolhida
  private int getPosition(K key) {
    String keyStr = key.toString();
    if (hashFunction.equals("divisao")) {
      return hashDivisao(keyStr);
    } else if (hashFunction.equals("djb2")) {
      return hashDJB2(keyStr);
    } else {
      throw new IllegalArgumentException("Função de hash inválida: " + hashFunction);
    }
  }

  // Retorna o valor associado à chave na tabela hash
  public LinkedList<V> get(K key) {
    if (key == null)
      return null;

    int position = getPosition(key);
    LinkedList<V> valuesFound = new LinkedList<>();

    if (hashTable[position] == null) {
      return null;
    } else {
      LinkedList<HashEntry<K, V, N>> currentList = hashTable[position];
      for (HashEntry<K, V, N> entry : currentList) {
        if (key.equals(entry.key)) {
          valuesFound.add(entry.value);
        }
      }
      return valuesFound;
    }
  }

  // Retorna o arquivo associado à chave na tabela hash
  public LinkedList<N> getN(K key) {
    if (key == null)
      return null;

    int position = getPosition(key);
    LinkedList<N> namesFound = new LinkedList<>();

    if (hashTable[position] == null) {
      return null;
    } else {
      LinkedList<HashEntry<K, V, N>> currentList = hashTable[position];
      for (HashEntry<K, V, N> entry : currentList) {
        if (key.equals(entry.key)) {
          namesFound.add(entry.nomeArq);
        }
      }
      return namesFound;
    }
  }

  // Insere uma nova entrada na tabela hash
  public boolean put(K key, V value, N nome) {
    if (key == null)
      return false;

    int position = getPosition(key);
    LinkedList<HashEntry<K, V, N>> currentList = hashTable[position];

    if (currentList == null) {
      currentList = new LinkedList<>();
    } else {
      for (HashEntry<K, V, N> entry : currentList) {
        if (key.equals(entry.key) && value.equals(entry.value)) {
          return false; // Valor duplicado
        }
      }
    }

    currentList.add(new HashEntry<>(key, value, nome));
    hashTable[position] = currentList;

    return true;
  }

  // Imprime o conteúdo da tabela hash
  public void print() {
    for (int i = 0; i < hashTable.length; i++) {
      System.out.println("---------------");
      System.out.println("Position " + i + ":");
      if (hashTable[i] == null) {
        System.out.println("Empty position");
      } else {
        for (HashEntry<K, V, N> entry : hashTable[i]) {
          System.out.print(entry + "  -  ");
        }
        System.out.println();
      }
    }
  }
}
