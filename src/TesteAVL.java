// Classe de teste separada
public class TesteAVL {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /*
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
        */

        ////////////////////////////

        // TESTES ADICIONAIS

        // Teste 1: Inserir números em ordem crescente
        System.out.println("Teste 1: Inserindo números de 1 a 9:");
        for (int i = 1; i <= 9; i++) {
            tree.add(i);
            System.out.print("In-order após inserir " + i + ": ");
            tree.inOrder();
        }

        // Apresentar a altura da árvore
        System.out.println("Altura da árvore após inserir 1 a 9: " + tree.height());

        // Limpar o conteúdo da árvore
        tree.clear();
        System.out.println("Árvore limpa. Está vazia? " + tree.isEmpty());

        // Teste 2: Inserir números em ordem decrescente
        System.out.println("\nTeste 2: Inserindo números de 9 a 1:");
        for (int i = 9; i >= 1; i--) {
            tree.add(i);
            System.out.print("In-order após inserir " + i + ": ");
            tree.inOrder();
        }

        // Apresentar a altura da árvore
        System.out.println("Altura da árvore após inserir 9 a 1: " + tree.height());

        // Limpar o conteúdo da árvore
        tree.clear();
        System.out.println("Árvore limpa. Está vazia? " + tree.isEmpty());

        // Teste 3: Inserir números em ordem aleatória
        int[] randomNumbers = {5, 2, 8, 1, 6, 3, 7, 4, 9};
        System.out.println("\nTeste 3: Inserindo números aleatórios:");
        for (int num : randomNumbers) {
            tree.add(num);
            System.out.print("In-order após inserir " + num + ": ");
            tree.inOrder();
        }

        // Apresentar a altura da árvore
        System.out.println("Altura da árvore após inserções aleatórias: " + tree.height());

        // Verificar se a árvore contém certos elementos
        System.out.println("A árvore contém 5? " + tree.contains(5));
        System.out.println("A árvore contém 10? " + tree.contains(10));

        // Limpar o conteúdo da árvore
        tree.clear();
        System.out.println("Árvore limpa. Está vazia? " + tree.isEmpty());

        // Teste 4: Inserir e remover elementos
        System.out.println("\nTeste 4: Inserir e remover elementos:");
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);
        tree.add(10);
        tree.add(25);

        System.out.print("In-order após inserções: ");
        tree.inOrder();

        System.out.println("Altura da árvore: " + tree.height());

        tree.remove(70);
        System.out.print("In-order após remover 70: ");
        tree.inOrder();

        tree.remove(50);
        System.out.print("In-order após remover 50: ");
        tree.inOrder();

        System.out.println("Altura da árvore após remoções: " + tree.height());

        // Verificar se a árvore contém certos elementos após remoções
        System.out.println("A árvore contém 70? " + tree.contains(70));
        System.out.println("A árvore contém 50? " + tree.contains(50));
        System.out.println("A árvore contém 40? " + tree.contains(40));
    }
}