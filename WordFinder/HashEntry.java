public class HashEntry<K, V, N> {
  K key; //Chave do valor armazenado
  V value; //Valor armazenado
  N nomeArq; //Nome do arquivo

  public HashEntry(K key, V value, N nomeArq) {
    this.key = key;
    this.value = value;
    this.nomeArq = nomeArq;
  }

  public String toString() {
    return "(" + key + ", " + value + ", " + nomeArq + ")";
  }
}