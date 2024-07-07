// Classe de teste separada
public class TesteAVL {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Incluir os números 1, 2, 3, 4, 5, 6, 7, 8, 9, nesta ordem, na árvore
        for (int i = 1; i <= 9; i++) {
            tree.add(i);
        }

        // Apresentar a visualização da árvore (caminhamento central)
        System.out.println("Árvore após adicionar os números de 1 a 9 (in-order):");
        tree.inOrder();

        // Apresentar a altura da árvore
        System.out.println("Altura da árvore: " + tree.height());

        // Limpar o conteúdo da árvore
        tree.clear();
        System.out.println("Árvore limpa. Está vazia? " + tree.isEmpty());

        // Incluir os números 9, 8, 7, 6, 5, 4, 3, 2, 1, nesta ordem, na árvore
        for (int i = 9; i >= 1; i--) {
            tree.add(i);
        }

        // Apresentar o conteúdo da árvore usando um caminhamento central
        System.out.println("Árvore após adicionar os números de 9 a 1 (in-order):");
        tree.inOrder();
    }
}