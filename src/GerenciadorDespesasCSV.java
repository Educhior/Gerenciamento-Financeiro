import java.io.*;
import java.util.List;

public class GerenciadorDespesasCSV {

    private static final String ARQUIVO_DESPESAS = "despesas.csv";

    public static void salvarDespesas(List<Despesa> despesas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DESPESAS, true));

        for (Despesa despesa : despesas) {
            String linha = despesa.getId() + "," + despesa.getValor() + "," + despesa.getDescricao() + "," 
                           + despesa.getCategoria() + "," + despesa.getDataHora();
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }

    public static int getProximoIdDespesa() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DESPESAS));
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
