/**
 * CLASSE BinarySearchTree
 * Trabalhando com árvore binária de pesquisa
 * */
import java.util.LinkedList;
import java.util.Queue;


class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Integer v) {

        Node prev, current;

        // cria um novo nodo
        Node node = new Node();

        // atribui o valor recebido ao item de dados do nodo
        node.element = v;
        node.right = null;
        node.left = null;

        // se a raiz está nula, a árvore está vazia
        if (root == null) {
            root = node;
        } else {
            current = root;
            // percorre a árvore
            while (true) {
                prev = current;
                // ir para esquerda
                if (v <= current.element) {
                    current = current.left;
                    if (current == null) {
                        // insere na subárvore da esquerda
                        prev.left = node;
                        return;
                    }
                }
                // ir para direita
                else {
                    current = current.right;
                    if (current == null) {
                        // insere na subárvore da direita
                        prev.right = node;
                        return;
                    }
                }
            }
        }
    }

    public Node contains(Integer v) {
        // se arvore vazia
        if (root == null)
            return null;

        // começa a procurar desde raiz
        Node current = root;
        // enquanto nao encontrou
        while (current.element != v) {
            if (v < current.element)
                current = current.left; // caminha para esquerda
            else
                current = current.right; // caminha para direita

            // encontrou uma folha -> sai
            if (current == null)
                return null;
        }

        // terminou o laço while e chegou aqui é pq encontrou item
        return current;
    }


    public boolean remove(Integer v) {
        // se arvore vazia
        if (root == null)
            return false;

        Node current = root;
        Node father = root;
        boolean child_left = true;

        // buscando o valor
        while (current.element != v) {
            // enquanto nao encontrou
            father = current;
            // caminha para esquerda
            if (v < current.element) {
                current = current.left;
                // é filho a esquerda? sim
                child_left = true;
            }
            // caminha para direita
            else {
                current = current.right;
                // é filho a esquerda? NAO
                child_left = false;
            }
            // encontrou uma folha -> sai
            if (current == null)
                return false;
        }
        // Se nao possui nenhum filho (é uma folha), elimine-o
        if (current.left == null && current.right == null) {
            // se raiz
            if (current == root)
                root = null;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = null;
                // se for filho a direita do pai
            else
                father.right = null;
        }
        // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
        else if (current.right == null) {
            // se raiz
            if (current == root)
                root = current.left;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.left;
                // se for filho a direita do pai
            else
                father.right = current.left;
        }
        // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
        else if (current.left == null) {
            // se raiz
            if (current == root)
                root = current.right;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.right;
                // se for  filho a direita do pai
            else
                father.right = current.right;
        }
        // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
        else {
            Node successor = node_successor(current);
            // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
            // se raiz
            if (current == root)
                root = successor;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = successor;
                // se for filho a direita do pai
            else
                father.right = successor;
            // acertando o ponteiro a esquerda do sucessor agora que ele assumiu
            successor.left = current.left;
            // a posição correta na arvore
        }
        return true;
    }

    // O sucessor é o nodo mais a esquerda da subarvore a direita do nodo que foi passado como parâmetro do método
    public Node node_successor(Node node) {
        Node father_successor = node;
        Node successor = node;
        Node current = node.left;

        // enquanto nao chegar no nodo mais a esquerda
        while (current != null) {
            father_successor = successor;
            successor = current;
            // caminha para a esquerda
            current = current.left;
        }
        // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
        if (successor != node.right) {
            // pai herda os filhos do sucessor que sempre serão a direita
            father_successor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    void clearTree() {
        root = null;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.print(current.element + " ");
            inOrder(current.right);
        }
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.element + " ");
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.left);
            postOrder(current.right);
            System.out.print(current.element + " ");
        }
    }

    public int height(Node current) {
        if (current == null || (current.left == null && current.right == null)) {
            return 0;
        } else {
            if (height(current.left) > height(current.right))
                return (1 + height(current.left));
            else
                return (1 + height(current.right));
        }
    }

    public int countNodes(Node current) {
        if (current == null)
            return 0;
        else
            return (1 + countNodes(current.left) + countNodes(current.right));
    }

    public Node getRoot() {
        return root;
    }

    public void orders() {
        System.out.print("\n Caminhamento Central (in-order): ");
        inOrder(root);
        System.out.print("\n Exibindo em Pós-ordem (post-order): ");
        postOrder(root);
        System.out.print("\n Exibindo em Pré-ordem (pre-order): ");
        preOrder(root);
        /* DEPOIS DE IMPLEMENTAR OS SEUS MÉTODOS, DESCOMENTE AS LINHAS ABAIXO PARA APRESENTAR OS DADOS */
        System.out.print("\n Exibindo em Largura (breadth-first): ");
        breadthFirstOrder(root);

    }

    public void treeInfo() {
        System.out.println("Altura da arvore: " + height(root));
        System.out.println("Quantidade de Nós: " + countNodes(root));
        /* DEPOIS DE IMPLEMENTAR OS SEUS MÉTODOS, DESCOMENTE AS LINHAS ABAIXO PARA APRESENTAR OS DADOS */
        System.out.println("Nível do menor nodo: " + minNodeLevel(root, 0));
        System.out.println("Diferença entre o valor máximo e a raiz: " + diffMaxRoot());
        System.out.println("Contagem dos nodos internos (galhos): " + countInternalNodes(root));
        System.out.println("Soma dos valores de nodos externos (folhas): " + sumExternalNodes(root));
    }

    public void printTree() {
        if (root != null) {
            TreeFormatter formatter = new TreeFormatter();
            System.out.println(formatter.topDown(root));
        } else {
            System.out.println("Árvore vazia!");
        }
    }

    /**
     * Método minNodeLevel(...)
     * método retorna o nível do nodo com menor valor existente na árvore
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor do menor nodo da árvore
     */

    public int minNodeLevel(Node current, int level) {
        if (root == null) { // Retorna '-1' caso a árvore não tenha nodos
            return -1;
        }
        if (current == null) { // Condicional para quando o algoritmo atinge um nodo nulo (final)
            return Integer.MAX_VALUE; // Retorna o maior valor possível de Integer no Java para não atrapalhar na comparação da função Math de mínimos
        }
        if (current.left == null && current.right == null) { // Se for um nó folha, retora o nível atual
            return level;
        } // Abaixo calcula recursivamente o nível mínimo nos filhos esquerdo e direito
        return Math.min(minNodeLevel(current.left, level + 1), minNodeLevel(current.right, level + 1));
    }

    /**
     * Método diffMaxRoot(...)
     * método retorna a diferença entre o valor do nodo máximo da árvore e a raiz
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor da subtração do valor do nodo de maior valor com o valor do nodo raiz
     */

    public int diffMaxRoot() {
        if (root == null) { // Retorna '0' caso a árvore esteja vazia
            return 0;
        }
        return maxValue(root) - root.element; // Retorna (e chama) o método maxValue com a raíz como parâmetro, subtraído do valor da raíz
    }
    private int maxValue(Node current) {
        if (current.right == null) { // Caso o nodo da direita seja nulo, retorna o valor do nodo atual
            return current.element;
        }
        return maxValue(current.right); // Retorna recursivamente o próprio método passando como parâmetro o nodo filho da direita do nodo atual
    }

    /**
     * Método countInternalNodes(...)
     * método retorna a quantidade de nodos internos (galhos) de uma árvore
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor inteiro correspondente a quantidade de nodos folha
     */

    public int countInternalNodes(Node current) {
        if (current == null || (current.left == null && current.right == null)) { // Retorna '0' caso o nodo atual seja nulo ou não tenha filhos
            return 0;
        }
        return 1 + countInternalNodes(current.left) + countInternalNodes(current.right); // Retorna 1 + a contagem recursiva dos filhos da esquerda e direita
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** método retona a soma dos valores de todos os nodos externos (folhas) de uma árvore
     * @param defina a necessidade de parâmetros de acordo com a sua implementaçãor
     * @return valor inteiro correspondente a soma dos valores de todos os nodos folha
     */

    public int sumExternalNodes(Node current) {
        // Se o nodo atual é nulo, retorna 0 (arvore vazia ou chegou ao final de um ramo)
        if (current == null) {
            return 0;
        }

        // Se o nodo atual é uma folha (não tem filhos), retorna o valor do nodo
        if (current.left == null && current.right == null) {
            return current.element;
        }

        // Caso contrario, soma recursivamente os valores das folhas dos sub-ramos esquerdo e direito
        return sumExternalNodes(current.left) + sumExternalNodes(current.right);

    }

    /**
     * método que imprime o caminhamento em largura
     * @param defina parâmetros caso haja necessidade na sua implementação
     */

    public void breadthFirstOrder(Node root) {

        // se a árvore está vazia
        if (root == null) {
            return;
        }

        // uma fila para armazenar os nodos
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // remove o nodo da frente da fila e imprime
            Node current = queue.poll();
            System.out.print(current.element + " ");

            // adiciona os filhos do nodo atual a fila
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * método método que retorna a soma de valores de uma sequência de nodos (deve incluir o valor do nodo inicial, mas não do nodo final)
     * @param start valor que corresponde ao nodo de início
     * @param end valor que corresponde ao nodo de fim
     * @param defina outros caso haja necessidade na sua implementação
     * @return valor inteiro correspondente a soma dos nodos do caminho determinado
     */

    public int sumBetween(int start, int end) {
        return sumBetween(root, start, end, false);
    }

    private int sumBetween(Node node, int start, int end, boolean started) {

        // se o nodo atual é nulo, retorna 0
        if (node == null) {
            return 0;
        }

        int sum = 0;

        // verifica se devemos começar a somar
        if (node.element == start) {
            started = true;
            sum += node.element;
        }

        // se já começamos a somar, adiciona o valor do nodo atual
        if (started && node.element != end) {
            sum += node.element;
        }

        // se atingimos o nodo final, retorna a soma
        if (node.element == end) {
            return sum;
        }

        // continua a busca recursivamente nos sub-ramos esquerdo e direito
        sum += sumBetween(node.left, start, end, started);
        sum += sumBetween(node.right, start, end, started);

        return sum;
    }



}