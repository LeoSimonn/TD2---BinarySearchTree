
public class AVLTree {

    private class Node {
        int element;
        Node left, right;
        int height;

        Node(int element) {
            this.element = element;
            this.height = 1; // Inicialmente, a altura de um nodo é 1
        }
    }

    private Node root;

    /** Remover elementos na árvore */

    public void remove(int element) {
        root = remove(root, element); // Remove recursivamente a partir da raiz
    }

    private Node remove(Node node, int element) {
        if (node == null) {
            return node;
        }

        if (element < node.element) {
            node.left = remove(node.left, element);
        } else if (element > node.element) {
            node.right = remove(node.right, element);
        } else {
            // Nodo com apenas um filho ou sem filhos
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;

                // Sem filhos
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp; // Copia o conteúdo do filho não nulo
                }
            } else {
                // Nodo com dois filhos: obter o menor valor da subárvore direita
                Node temp = minValueNode(node.right);

                // Copiar o valor do menor nodo para este nodo
                node.element = temp.element;

                // Remover o sucessor
                node.right = remove(node.right, temp.element);
            }
        }

        // Se a árvore tinha apenas um nodo, retorna
        if (node == null) {
            return node;
        }

        // Atualiza a altura do nodo atual
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Balancear o nodo
        return balance(node);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        // Encontra o menor valor da subárvore esquerda
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /** Adicionar elementos na árvore */
    public void add(int element) {
        root = add(root, element); // Adiciona recursivamente a partir da raiz
    }

    private Node add(Node node, int element) {
        // Se o nodo atual é nulo, cria um novo nodo com o valor
        if (node == null) {
            return new Node(element);
        }

        // Compara o valor para decidir em qual subárvore inserir
        if (element < node.element) {
            node.left = add(node.left, element);
        } else if (element > node.element) {
            node.right = add(node.right, element);
        } else {
            return node; // Já existe elemento na arvore
        }

        // Atualiza a altura do nodo atual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Balanceia o nodo e retorna o novo nodo balanceado
        return balance(node);
    }

    /** Retornar o pai de um elemento */
    public Integer getParent(int element) {
        return getParent(root, null, element); // Inicia a busca a partir da raiz
    }

    private Integer getParent(Node node, Node parent, int element) {
        if (node == null) {
            return null;
        }

        // Se encontrou o elemento, retorna o valor do pai
        if (node.element == element) {
            return (parent != null) ? parent.element : null;
        }

        // Continua a busca na subárvore esquerda ou direita
        if (element < node.element) {
            return getParent(node.left, node, element);
        } else {
            return getParent(node.right, node, element);
        }
    }

    /** Limpar o conteúdo da árvore */
    public void clear() {
        root = null;
    }

    /** Verificar se um elemento está armazenado na árvore ou não */
    public boolean contains(int element) {
        return contains(root, element); // Inicia a busca a partir da raiz
    }

    private boolean contains(Node node, int element) {
        // Se o nodo atual é nulo, o elemento não está na árvore
        if (node == null) {
            return false;
        }

        // Compara o valor para decidir em qual subárvore buscar
        if (element < node.element) {
            return contains(node.left, element);
        } else if (element > node.element) {
            return contains(node.right, element);
        } else {
            return true; // Encontrou o elemento
        }
    }

    /** Verificar qual é a altura da árvore */
    public int height() {
        return height(root); // Retorna a altura da árvore a partir da raiz
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height; // Retorna a altura do nodo
    }

    /** Verificar quantos elementos tem na árvore */
    public int size() {
        return size(root); // Retorna o tamanho da árvore a partir da raiz
    }

    private int size(Node node) {
        if (node == null) {
            return 0; // Se o nodo é nulo, retorna 0
        }
        // Retorna 1 (o próprio nodo) + tamanho da subárvore esquerda + tamanho da
        // subárvore direita
        return 1 + size(node.left) + size(node.right);
    }

    /** Verificar se a árvore está vazia ou não */
    public boolean isEmpty() {
        return root == null; // Retorna true se a raiz for nula
    }

    /** Retornar os elementos da árvore em uma lista usando caminhamento central */
    public void inOrder() {
        inOrder(root); // Inicia a travessia a partir da raiz
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left); // Percorre a subárvore esquerda
            System.out.print(node.element + " "); // Visita o nodo atual
            inOrder(node.right); // Percorre a subárvore direita
        }
    }

    // Balanceamento da árvore AVL
    private Node balance(Node node) {
        int balanceFator = getBalanceFator(node);

        // Rotação direita
        if (balanceFator > 1) {
            if (getBalanceFator(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        // Rotação esquerda
        if (balanceFator < -1) {
            if (getBalanceFator(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node; // Nodo já balanceado
    }

    // fator do balanceamento
    private int getBalanceFator(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // rotação à direita
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna o novo nodo raiz
        return x;
    }

    // rotação à esquerda
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna o novo nodo raiz
        return y;
    }

}
