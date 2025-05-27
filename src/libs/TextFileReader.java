package libs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public static MeuArrayList<String> readLines(String filename) throws IOException {
        MeuArrayList<String> lines = new MeuArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.append(line);
                }
            }
        }
        return lines;
    }
}