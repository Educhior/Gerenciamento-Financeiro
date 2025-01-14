import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TelaPrincipal extends JFrame {
    private JLabel labelCaixaTotal;
    private JLabel labelDespesas;
    private JLabel labelReceitas;
    private JTable tabelaDespesas;
    private DefaultTableModel modeloTabela;
    private double caixaTotal = 0.00;
    private double despesas = 0.00;
    private double receitas = 0.00;

    public TelaPrincipal() {
        setTitle("Gerenciador Financeiro - Tela Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior com resumo de dados
        JPanel painelResumo = new JPanel(new GridLayout(1, 4, 10, 10));
        painelResumo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelCaixaTotal = new JLabel("R$ " + caixaTotal, SwingConstants.CENTER);
        labelDespesas = new JLabel("R$ " + despesas, SwingConstants.CENTER);
        labelReceitas = new JLabel("R$ " + receitas, SwingConstants.CENTER);

        painelResumo.add(criarPainelResumo("Caixa Total (R$)", labelCaixaTotal, new Color(76, 175, 80)));
        painelResumo.add(criarPainelResumo("Despesas (R$)", labelDespesas, new Color(244, 67, 54)));
        painelResumo.add(criarPainelResumo("Receitas (R$)", labelReceitas, new Color(33, 150, 243)));

        // Tabela para exibir despesas
        modeloTabela = new DefaultTableModel(new Object[]{"Data", "Tipo de Entrada", "Descrição", "Categoria", "Valor (R$)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede edição
            }
        };
        tabelaDespesas = new JTable(modeloTabela);

        // Impede que as colunas sejam movidas
        JTableHeader cabecalho = tabelaDespesas.getTableHeader();
        cabecalho.setReorderingAllowed(false);

        tabelaDespesas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String tipoEntrada = (String) table.getValueAt(row, 1); // Coluna "Tipo de Entrada"
                if ("Entrada".equalsIgnoreCase(tipoEntrada)) {
                    component.setBackground(new Color(200, 255, 200)); // Verde para entradas
                } else if ("Saída".equalsIgnoreCase(tipoEntrada)) {
                    component.setBackground(new Color(255, 200, 200)); // Vermelho para saídas
                } else {
                    component.setBackground(Color.WHITE); // Branco padrão
                }
                return component;
            }
        });
        JScrollPane scrollTabela = new JScrollPane(tabelaDespesas);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Lista de Despesas"));

        // Painel inferior com botões
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarReceita = new JButton("Adicionar Receita");
        JButton botaoAdicionarDespesa = new JButton("Adicionar Despesa");
        JButton sair = new JButton("Sair");

        painelBotoes.add(botaoAdicionarReceita);
        painelBotoes.add(botaoAdicionarDespesa);
        painelBotoes.add(sair);

        // Painéis à janela principal
        add(painelResumo, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ação para o botão de adicionar receita
        botaoAdicionarReceita.addActionListener(e -> {
            TelaAdicionarReceita telaAdicionarReceita = new TelaAdicionarReceita();
            telaAdicionarReceita.setVisible(true);
            atualizarValores();
            atualizarResumo();
            atualizarTabelaDespesas();
        });

        // Ação para o botão de adicionar despesa
        botaoAdicionarDespesa.addActionListener(e -> {
            TelaAdicionarDespesa telaAdicionarDespesa = new TelaAdicionarDespesa();
            telaAdicionarDespesa.setVisible(true);
            atualizarValores();
            atualizarResumo();
            atualizarTabelaDespesas();
        });

        // Ação para o botão de sair
        sair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Atualizar os valores a partir dos arquivos CSV
        atualizarValores();
        atualizarResumo();
        atualizarTabelaDespesas();
    }

    private JPanel criarPainelResumo(String titulo, JLabel valorLabel, Color cor) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(BorderFactory.createLineBorder(cor, 2));

        JLabel labelTitulo = new JLabel(titulo, SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(labelTitulo, BorderLayout.NORTH);

        valorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valorLabel.setForeground(cor);
        painel.add(valorLabel, BorderLayout.CENTER);

        JButton botaoDetalhes = new JButton("Ver detalhes");
        botaoDetalhes.addActionListener(e -> JOptionPane.showMessageDialog(null, "Exibindo detalhes sobre: " + titulo));
        painel.add(botaoDetalhes, BorderLayout.SOUTH);

        return painel;
    }

    private void atualizarResumo() {
        // Atualiza os valores exibidos no painel de resumo
        labelCaixaTotal.setText("R$ " + caixaTotal);
        labelDespesas.setText("R$ " + despesas);
        labelReceitas.setText("R$ " + receitas);
    }

    private void atualizarValores() {
        // Atualiza as receitas e despesas a partir dos arquivos CSV
        receitas = GerenciadorCSV.obterValorTotal("receitas");
        despesas = GerenciadorCSV.obterValorTotal("despesas");
        caixaTotal = receitas - despesas;
    }

    private void atualizarTabelaDespesas() {
        modeloTabela.setRowCount(0); // Limpa a tabela

        // Carregar as despesas reais a partir do arquivo CSV
        List<String[]> despesasCSV = GerenciadorCSV.obterDespesas();

        // Adiciona as despesas na tabela
        for (String[] linha : despesasCSV) {
            String data = linha[4]; // Data
            String tipoEntrada = "Saída"; // Tipo fixo para despesas
            String descricao = linha[2]; // Descrição
            String categoria = linha[3]; // Categoria
            String valor = linha[1]; // Valor

            modeloTabela.addRow(new Object[]{data, tipoEntrada, descricao, categoria, valor});
        }

        // Carregar as receitas reais a partir do arquivo CSV
        List<String[]> receitasCSV = GerenciadorCSV.obterReceitas();

        // Adiciona as receitas na tabela
        for (String[] linha : receitasCSV) {
            String data = linha[4]; // Data
            String tipoEntrada = "Entrada"; // Tipo fixo para receitas
            String descricao = linha[2]; // Descrição
            String categoria = linha[3]; // Categoria
            String valor = linha[1]; // Valor

            modeloTabela.addRow(new Object[]{data, tipoEntrada, descricao, categoria, valor});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
        });
    }
}
