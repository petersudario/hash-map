import libs.TextFileReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Carregar arquivo de nomes de dentro de src/libs
        URL resource = TextFileReader.class.getResource("/libs/female_names.txt");
        if (resource == null) {
            System.err.println("Arquivo female_names.txt não encontrado.");
            return;
        }
        String filename = resource.getPath();

        AbstractHashTable<String> bitMix = new BitMixHashTable<>();

        try {
            // Leitura dos nomes
            ArrayList<String> names = TextFileReader.readLines(filename);

            // Relatório para BitMixHashTable
            long startInsertBM = System.nanoTime();
            for (int i = 0; i < names.size(); i++) {
                bitMix.insert(names.get(i));
            }
            long endInsertBM = System.nanoTime();

            long startSearchBM = System.nanoTime();
            for (int i = 0; i < names.size(); i++) {
                bitMix.contains(names.get(i));
            }
            long endSearchBM = System.nanoTime();

            System.out.println("=== Relatório de Desempenho e Colisões ===\n");

            // BitMix
            System.out.println("--- BitMixHashTable ---");
            System.out.printf("Número total de colisões: %d\n", bitMix.getCollisions());
            System.out.printf("Tempo inserção: %.3f ms\n", (endInsertBM - startInsertBM) / 1_000_000.0);
            System.out.printf("Tempo busca:    %.3f ms\n", (endSearchBM - startSearchBM) / 1_000_000.0);
            System.out.println("Colisões por bucket (clusterização):");
            int[] distBM = bitMix.getDistribution();
            for (int i = 0; i < distBM.length; i++) {
                System.out.printf("  Bucket %2d: %d entradas\n", i, distBM[i]);
            }
            System.out.println();


        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}


