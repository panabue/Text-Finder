import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
  // Mapa para armazenar o nome do documento e a árvore de Huffman associada
  private static Map<String, ArvoreHuffman> huffmanIndex = new HashMap<>();

  public static void main(String[] args) {

    System.out.print("> Inserir documentos: ");
    Scanner scanner = new Scanner(System.in);
    // Caminho para o diretório contendo os arquivos .txt que foram convertidos
    String caminhoDiretorio = scanner.nextLine();

    // Verifica se o usuário forneceu um caminho para uma pasta válida
    try {
      Path caminhoDiretorio2 = Path.of(caminhoDiretorio);
      // Percorre todos os arquivos no diretório
      Files.walk(caminhoDiretorio2)
          .filter(Files::isRegularFile)
          .filter(p -> p.toString().endsWith(".txt"))
          .forEach(caminhoArquivo -> {
            try {
              String input = Files.readString(caminhoArquivo);
            } catch (IOException e) {
              System.err.println("Erro ao ler o arquivo " + caminhoArquivo.getFileName() + ": " + e.getMessage());
            }
          });

      System.out.println("Documentos inseridos com sucesso!");
      System.out.println();
    } catch (IOException e) {
      System.err.println("Erro ao inserir documentos: " + e.getMessage());
      return;
    }

    // Recebe do usuário a função hash que sera utilizada
    System.out.print("> Qual a função de hashing (divisao/djb2): ");
    String funcaoHash = scanner.nextLine();
    if (!funcaoHash.equals("divisao") && !funcaoHash.equals("djb2")) {
      System.out.println("Método de hash inválido. Escolha entre 'divisao' ou 'djb2'.");
      return;
    }

    // Cria a hashtable com 10 posições
    HashTable<Integer, String, String> testHash = new HashTable<>(10, funcaoHash);

    // Indexa os documentos na hashtable
    try {
      Path caminhoDiretorio2 = Path.of(caminhoDiretorio);
      // Percorre todos os arquivos no diretório
      Files.walk(caminhoDiretorio2)
          .filter(Files::isRegularFile)
          .filter(p -> p.toString().endsWith(".txt"))
          .forEach(caminhoArquivo -> {
            try {
              String input = Files.readString(caminhoArquivo); // Lê o conteúdo do arquivo

              ArvoreHuffman huffman = new ArvoreHuffman(input); // Criação da árvore de Huffman
              String compressed = huffman.compress(input); // Comprime o conteúdo do arquivo

              // Coloca o nome do arquivo e sua árvore de Huffman no mapa
              huffmanIndex.put(caminhoArquivo.getFileName().toString(), huffman);

              int t = testHash.hashDivisao(compressed); // Calcula a chave da hash

              // Insere na hashtable a chave, o conteudo do arquivo comprimido e seu nome
              testHash.put(t, compressed, caminhoArquivo.getFileName().toString());

            } catch (IOException e) {
              System.err.println("Erro ao ler o arquivo " + caminhoArquivo.getFileName() + ": " + e.getMessage());
              return;
            }
          });

      System.out.println("Documentos indexados com sucesso!");
      System.out.println();
    } catch (IOException e) {
      System.err.println("Erro ao indexar documentos: " + e.getMessage());
      return;
    }

    // Loop de busca por palavras
    while (true) {
      System.out.print("> Buscar palavra: ");
      String palavra = scanner.nextLine();

      // Caso o usuário digite "sair" o programa é encerrado
      if (palavra.equalsIgnoreCase("sair")) {
        System.out.println("Encerrando o programa.");
        break;
      }

      boolean palavraEncontrada = false; // Flag para rastrear se a palavra foi encontrada
      List<String> documentosEncontrados = new ArrayList<>(); // Lista para armazenar os nomes dos documentos em que a
                                                              // palavra foi encontrada

      // Percorre a hashtable para encontrar a palavra
      for (int f = 0; f < testHash.size; f++) {
        LinkedList<String> valuesFound = testHash.get(f); // Guarda os conteúdos dos arquivos da hahshtable
        LinkedList<String> namesFound = testHash.getN(f); // Guarda os nomes dos arquivos da hashtable

        if (valuesFound != null && namesFound != null) {
          if (valuesFound.size() == namesFound.size()) {
            // Percorre os valores e nomes encontrados na hashtable
            for (int i = 0; i < valuesFound.size(); i++) {
              String value = valuesFound.get(i); // Obter valor(conteúdo) compactado
              String nomeArq = namesFound.get(i); // Obter nome do arquivo

              ArvoreHuffman huffman = huffmanIndex.get(nomeArq); // Recuperar a árvore de Huffman associada
              Trie trie = new Trie(); // Criação da árvore de busca trie

              if (huffman != null) {
                String x = huffman.decompress(value); // Decomprime o conteúdo do arquivo
                trie.insert(x); // Insere o conteúdo decodificado na árvore de busca trie

                // Verifica se a palavra está presente na árvore de busca trie
                if (trie.searchWord(palavra)) {
                  documentosEncontrados.add(nomeArq); // Adicionar à lista de documentos encontrados
                  palavraEncontrada = true; // Atualizar a flag
                }
              }
            }
          } else {
            System.out.println("Erro: listas de valores e nomes têm tamanhos diferentes.");
          }
        }
      }

      // Exibir os resultados
      if (palavraEncontrada) {
        System.out.println("A palavra \"" + palavra + "\" foi encontrada nos seguintes documentos:");
        // Exibir os nomes dos documentos em que a palavra foi encontrada
        for (String doc : documentosEncontrados) {
          System.out.println("- " + doc);
        }
      } else {
        System.out.println("A palavra \"" + palavra + "\" não foi encontrada em nenhum documento.");
      }

      System.out.println();
    }
  }
}