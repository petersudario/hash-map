package libs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utilitário para leitura de arquivos de texto em seu projeto.
 */
public class TextFileReader {
    /**
     * Lê todas as linhas não vazias do arquivo especificado e retorna um MeuArrayList<String>.
     * @param filename caminho para o arquivo de texto
     * @return linhas lidas do arquivo
     * @throws IOException se ocorrer erro de I/O
     */
    public static ArrayList<String> readLines(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }
}