public class Receita {
    private int id;
    private double valor;
    private String descricao;
    private String categoria;
    private String dataHora;

    public Receita(int id, double valor, String descricao, String categoria, String dataHora) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDataHora() {
        return dataHora;
    }
}
