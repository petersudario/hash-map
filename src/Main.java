
import libs.MeuArrayList;
import libs.TextFileReader;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        // Carregar arquivo de nomes de dentro de src/libs
        URL resource = TextFileReader.class.getResource("/libs/female_names.txt");
        if (resource == null) {
            System.err.println("Arquivo female_names.txt não encontrado.");
            return;
        }
        String filename = resource.getPath();

        // Instâncias das tabelas hash
        AbstractHashTable<String> bitMix = new BitMixHashTable<>();
        AbstractHashTable<String> fibo = new FibonacciHashTable<>();

        try {
            // Leitura dos nomes
            MeuArrayList<String> names = TextFileReader.readLines(filename);

            // Relatório para BitMixHashTable
            long startInsertBM = System.nanoTime();
            for (int i = 0; i < names.tamanho(); i++) {
                bitMix.insert(names.get(i));
            }
            long endInsertBM = System.nanoTime();

            long startSearchBM = System.nanoTime();
            for (int i = 0; i < names.tamanho(); i++) {
                bitMix.contains(names.get(i));
            }
            long endSearchBM = System.nanoTime();

            // Relatório para FibonacciHashTable
            long startInsertFH = System.nanoTime();
            for (int i = 0; i < names.tamanho(); i++) {
                fibo.insert(names.get(i));
            }
            long endInsertFH = System.nanoTime();

            long startSearchFH = System.nanoTime();
            for (int i = 0; i < names.tamanho(); i++) {
                fibo.contains(names.get(i));
            }
            long endSearchFH = System.nanoTime();

            // Imprimir relatório
            System.out.println("=== Relatório de Desempenho e Colisões ===\n");

            // BitMix
            System.out.println("--- BitMixHashTable ---");
            System.out.printf("Número total de colisões: %d\n", bitMix.getCollisions());
            System.out.printf("Tempo inserção: %.3f ms\n", (endInsertBM - startInsertBM) / 1_000_000.0);
            System.out.printf("Tempo busca:    %.3f ms\n", (endSearchBM - startSearchBM) / 1_000_000.0);
            System.out.println("Colisões por bucket (clusterização):");
            int[] distBM = bitMix.getDistribution();
            System.out.println();

            // Fibonacci
            System.out.println("--- FibonacciHashTable ---");
            System.out.printf("Número total de colisões: %d\n", fibo.getCollisions());
            System.out.printf("Tempo inserção: %.3f ms\n", (endInsertFH - startInsertFH) / 1_000_000.0);
            System.out.printf("Tempo busca:    %.3f ms\n", (endSearchFH - startSearchFH) / 1_000_000.0);
            System.out.println("Colisões por bucket (clusterização):");
            int[] distFH = fibo.getDistribution();

            System.out.println(fibo.contains("Maria")); // Exemplo de busca

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}

