import java.util.Scanner;

public class AVLTree {

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
            node = new Node();
            node.element = element;
        }

        // Compara o valor para decidir em qual subárvore inserir
        if (element < node.element) {
            node.left = add(node.left, element);
        } else if (element > node.element) {
            node.right = add(node.right, element);
        } else {
            return node; // Já existe elemento na árvore
        }

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
        return (node == null) ? 0 : 1 + Math.max(height(node.left), height(node.right));
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

        // Retorna o novo nodo raiz
        return y;
    }

    public int heightAjustado() { // Ajuste necessário para diferenciar a altura usada em outros métodos da AVL do que é mostrado no display
        return height(root) - 1; // A árvore vai ter altura -1 quando vazia, 0 quando tem somente a raíz e assim por diante
    }

    public void printTree() {
        if (root != null) {
            TreeFormatter formatter = new TreeFormatter();
            System.out.println(formatter.topDown(root));
        } else {
            System.out.println("Árvore vazia!");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        int op;
        int value;

        do {
            System.out.println("\nÁRVORE AVL");
            System.out.println("---------------------------------------");
            System.out.println("1 | Adicionar nodo");
            System.out.println("2 | Remover nodo");
            System.out.println("3 | Pesquisar nodo");
            System.out.println("4 | Exibir a árvore");
            System.out.println("5 | Mostrar informações");
            System.out.println("6 | Mostrar caminhamentos");
            System.out.println("7 | Esvaziar árvore");
            System.out.println("8 | Rodar testes do enunciado");
            System.out.println("0 | Sair do programa");
            System.out.println("---------------------------------------");
            System.out.println("Digite a opção desejada: ");

            op = scan.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    tree.add(value);
                }
                case 2 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    tree.remove(value);
                }
                case 3 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    if (tree.contains(value))
                        System.out.println("Valor encontrado!");
                    else
                        System.out.println("Valor não encontrado!");
                }
                case 4 -> {
                    tree.printTree();
                }
                case 5 -> {
                    System.out.println("Altura da árvore: " + tree.heightAjustado());
                    System.out.println("Número de elementos: " + tree.size());
                }
                case 6 -> {
                    System.out.println("Caminhamento central:");
                    tree.inOrder();
                }
                case 7 -> {
                    tree.clear();
                    System.out.println("Árvore esvaziada.");
                }
                case 8 -> {
                    // Rodar testes do enunciado
                    System.out.println("Inserindo elementos de 1 a 9 na árvore...\n");
                    tree = new AVLTree();
                    for (int i = 1; i <= 9; i++) {
                        tree.add(i);
                    }
                    System.out.println("Árvore após inserir 1 a 9:");
                    tree.printTree();
                    System.out.println("Altura da árvore: " + tree.heightAjustado());

                    System.out.println("\nEsvaziando a árvore...");
                    tree.clear();
                    System.out.println("Árvore limpa. Está vazia? " + tree.isEmpty());

                    System.out.println("\nInserindo elementos de 9 a 1 na árvore...\n");
                    for (int i = 9; i >= 1; i--) {
                        tree.add(i);
                    }
                    System.out.println("Árvore após inserir 9 a 1:");
                    tree.printTree();
                    System.out.println("Caminhamento central da árvore após a inserção:");
                    tree.inOrder();
                }
            }
        } while (op != 0);

        scan.close();
    }
}
