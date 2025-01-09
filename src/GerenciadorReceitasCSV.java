import java.io.*;
import java.util.List;

public class GerenciadorReceitasCSV {

    private static final String ARQUIVO_RECEITAS = "receitas.csv";

    public static void salvarReceitas(List<Receita> receitas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RECEITAS, true)); // Abrir para adicionar

        for (Receita receita : receitas) {
            String linha = receita.getId() + "," + receita.getValor() + "," + receita.getDescricao() + "," 
                           + receita.getCategoria() + "," + receita.getDataHora();
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }
}
