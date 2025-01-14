import java.io.*;
import java.util.List;

public class GerenciadorReceitasCSV {

    private static final String ARQUIVO_RECEITAS = "receitas.csv";

    public static void salvarReceitas(List<Receita> receitas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RECEITAS, true));

        for (Receita receita : receitas) {
            String linha = receita.getId() + "," + receita.getValor() + "," + receita.getDescricao() + "," 
                           + receita.getCategoria() + "," + receita.getDataHora();
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }

    public static int getProximoIdReceita() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RECEITAS));
        String linha;
        int idMax = 0;

        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(",");
            int id = Integer.parseInt(campos[0]);
            if (id > idMax) {
                idMax = id;
            }
        }

        reader.close();
        return idMax + 1;
    }
}
