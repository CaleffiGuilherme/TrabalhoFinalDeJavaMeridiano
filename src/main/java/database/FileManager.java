package database;

import java.io.*;

public class FileManager {
    public static void salvarDados(String caminho, String conteudo) throws IOException {
        File arquivo = new File(caminho);

        File diretorioPai = arquivo.getParentFile();
        if (diretorioPai != null && !diretorioPai.exists()) {
            diretorioPai.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write(conteudo);
        }
    }

    public static String carregarDados(String caminho) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString();
    }
}