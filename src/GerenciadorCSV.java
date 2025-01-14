import java.io.*;
import java.util.*;

public class GerenciadorCSV {

    public static double obterValorTotal(String tipo) {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(tipo + ".csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                double valor = Double.parseDouble(dados[1]);
                total += valor;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static List<String[]> obterReceitas() {
        return obterDados("receitas");
    }

    public static List<String[]> obterDespesas() {
        return obterDados("despesas");
    }

    private static List<String[]> obterDados(String tipo) {
        List<String[]> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tipo + ".csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) { // Garante que a linha tenha 5 elementos
                    dados.add(partes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }
}
