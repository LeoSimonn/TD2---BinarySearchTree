# Projeto de Implementação de Árvores Binárias de Pesquisa

## Integrantes
1. Leonardo Simon Monteiro
2. Bruno Coliselli Fernandes
3. Esthevan Pereira

## Descrição
Este projeto tem como objetivo implementar métodos em uma Árvore Binária de Pesquisa (BST) e estudar uma árvore balanceada (AVL ou Red-Black). O projeto é dividido em duas partes principais:
1. Implementação de métodos adicionais na classe `BinarySearchTree`.
2. Estudo, implementação e teste de uma árvore balanceada escolhida (AVL ou Red-Black).

## Estrutura do Projeto

### Parte 1: Implementação de Métodos na BinarySearchTree

Os seguintes métodos foram adicionados à classe `BinarySearchTree`:

- `minNodeLevel()`: Retorna o nível do nodo com o menor valor existente na árvore.
- `diffMaxRoot()`: Retorna a diferença entre o valor do nodo máximo da árvore e a raiz.
- `countInternalNodes()`: Retorna a quantidade de nodos internos (galhos) da árvore.
- `sumExternalNodes()`: Retorna a soma dos valores de todos os nodos externos (folhas) da árvore.
- `breadthFirstOrder()`: Imprime o caminhamento em largura.
- `sumBetween(int start, int end)`: Retorna a soma de valores de uma sequência de nodos entre dois valores especificados (inclusivo no início, exclusivo no fim).

### Parte 2: Estudo e Implementação de uma Árvore Balanceada

Foi escolhida a implementação da árvore AVL. O trabalho envolveu:

- Estudo das árvores AVL utilizando recursos como livros, artigos e simuladores.
- Implementação da árvore AVL seguindo a interface fornecida:
  - `add(obj)`
  - `getParent(obj)`
  - `clear()`
  - `contains(obj)`
  - `height()`
  - `size()`
  - `isEmpty()`
  - `inOrder()`

- Implementação de métodos adicionais necessários para o correto funcionamento e balanceamento da árvore.

### Testes e Verificação

Foram realizados testes na implementação da árvore AVL com os seguintes passos:
1. Instanciar a árvore AVL.
2. Incluir os números 1, 2, 3, 4, 5, 6, 7, 8, 9 nesta ordem.
3. Apresentar a visualização da árvore.
4. Apresentar a altura da árvore.
5. Limpar o conteúdo da árvore.
6. Incluir os números 9, 8, 7, 6, 5, 4, 3, 2, 1 nesta ordem.
7. Apresentar o conteúdo da árvore usando um caminhamento central.

### Vídeo Explicativo

Um vídeo explicativo foi elaborado, demonstrando:
- O funcionamento da árvore AVL, destacando os processos de inserção e balanceamento.
- A implementação realizada.

O vídeo pode ser acessado [aqui](#) (substitua com o link real do vídeo).
