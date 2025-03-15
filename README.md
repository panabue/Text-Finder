# Text-Finder
Sistema de indexação e busca de textos utilizando a compactação de Huffman, armazenamento em tabelas Hash e a indexação de palavras com a estrutura Trie.

## 1. Introdução e Objetivos

A partir da necessidade de agilidade na pesquisa científica, o presente projeto tem como objetivo trazer uma solução para tal demanda, diminuindo o tempo de busca por um tema ou tópico específico em diversos artigos selecionados pelo usuário. Através disso, é obtido uma agilidade em pesquisas para outros projetos, ocasionando facilidade e uma melhor experiência.

## 2. Descrição das estruturas de dados e corpus utilizado

Compactação de Huffman: Utilizada para compactar os artigos fornecidos pelo usuário.

Tabela Hash: Responsável por armazenar os textos compactados. Foram implementadas duas funções:

Hash por Divisão: Baseada na soma dos valores ASCII dos caracteres, com o cálculo de módulo pelo tamanho da tabela.

Hash DJB2: Utiliza deslocamento de bits e soma, gerando chaves com maior dispersão e menor chance de colisão.

Trie (Árvore Digital): Indexa palavras dos textos, permitindo buscas rápidas. Cada nó representa um caractere, formando caminhos que correspondem às palavras armazenadas.

O Corpus utilizado foram 30 artigos científicos do site arXiv e convertidos para .txt.

## 3. Metodologia de Compressão e Indexação

Os artigos foram transformados em .txt antes de serem compactados. Na compactação foi utilizado o algoritmo de Huffman para a compressão dos textos e cada artigo comprimido foi armazenado em um Tabela Hash, em que poderia ser armazenado pelo método de divisão, em que se baseia na soma do valor de cada caractere relativo a tabela ASCII, fazendo o cálculo da soma obtida pelo módulo do tamanho da tabela. Outro método de armazenamento na Tabela Hash é o DJB2, que transforma strings em números para indexação em tabelas hash. Após o armazenamento de todos os artigos listados é possível fazer a busca de palavras chave em todos os artigos, com a palavra digitada, é feita a descompressão de cada documento e busca da mesma. Terminando o processo de busca em todos os arquivos, aqueles que possuírem a palavra chave serão escritos no prompt.

## 4. Medição de Desempenho

Utilizando a função currentTimeMillis() para tempo de indexação e busca. No cálculo relacionado ao consumo de memória foi utilizada a função getRuntime(). Obtivemos os seguintes resultados:
Tempo de Indexação: 78945 milissegundos com o hash djb2 e 90851 milissegundos com o hash por divisão.
Tempo de Busca: A palavra buscada foi “of”, foram 39291 milissegundos para o hash djb2 e 66903 milissegundos para o hash por divisão.
Consumo de Memória: 6.128 MB utilizados pelo sistema.
Com os testes feitos é possível notar que o hash por divisão apresentou menor velocidade na indexação e busca. Como foi utilizado o método Trie, nota-se que houve um maior consumo de memória devido à sua estrutura.

# Como executar

1.
