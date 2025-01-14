import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class TelaPrincipal extends JFrame {
    private JLabel labelCaixaTotal;
    private JLabel labelDespesas;
    private JLabel labelReceitas;
    private JTable tabelaDespesas;
    private DefaultTableModel modeloTabela;
    private JTextField campoBusca;
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

        // Painel de busca
        JPanel painelBusca = new JPanel();
        painelBusca.add(new JLabel("Buscar:"));
        campoBusca = new JTextField(15);
        painelBusca.add(campoBusca);

        // ComboBox para filtro de tipo de entrada (Entrada, Saída, Todos)
        JComboBox<String> filtroTipo = new JComboBox<>(new String[]{"Todos", "Entrada", "Saída"});
        painelBusca.add(new JLabel("Filtro de Tipo:"));
        painelBusca.add(filtroTipo);

        // Painel superior que contém tanto o painel de resumo quanto o painel de busca
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout());
        painelSuperior.add(painelResumo, BorderLayout.CENTER);
        painelSuperior.add(painelBusca, BorderLayout.SOUTH);

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
        add(painelSuperior, BorderLayout.NORTH); // Adiciona o painel superior com resumo e busca
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

        // Ação para o campo de busca
        campoBusca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabela(campoBusca.getText(), filtroTipo.getSelectedItem().toString());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabela(campoBusca.getText(), filtroTipo.getSelectedItem().toString());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabela(campoBusca.getText(), filtroTipo.getSelectedItem().toString());
            }
        });

        // Ação para o filtro de tipo de entrada
        filtroTipo.addActionListener(e -> {
            filtrarTabela(campoBusca.getText(), filtroTipo.getSelectedItem().toString());
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

    private void filtrarTabela(String textoBusca, String tipoFiltro) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabela);
        tabelaDespesas.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filtroTexto = RowFilter.regexFilter("(?i)" + textoBusca);
        RowFilter<DefaultTableModel, Object> filtroTipo = null;

        if ("Entrada".equalsIgnoreCase(tipoFiltro)) {
            filtroTipo = RowFilter.regexFilter("Entrada", 1); // Filtra pela coluna "Tipo de Entrada"
        } else if ("Saída".equalsIgnoreCase(tipoFiltro)) {
            filtroTipo = RowFilter.regexFilter("Saída", 1); // Filtra pela coluna "Tipo de Entrada"
        }

        if (filtroTipo != null) {
            // Corrigido: agora passamos os filtros como uma lista
            RowFilter<DefaultTableModel, Object> filtroCombinado = RowFilter.andFilter(List.of(filtroTexto, filtroTipo));
            sorter.setRowFilter(filtroCombinado);
        } else {
            sorter.setRowFilter(filtroTexto);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
        });
    }
}
