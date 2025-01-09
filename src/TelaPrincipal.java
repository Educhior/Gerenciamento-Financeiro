import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {
    private JLabel labelCaixaTotal;
    private JLabel labelDespesas;
    private JLabel labelReceitas;
    private double caixaTotal = 15520.00;
    private double despesas = 530.00;
    private double receitas = 14990.00;

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

        // Placeholder para gráfico
        JPanel painelGrafico = new JPanel();
        painelGrafico.setBorder(BorderFactory.createTitledBorder("Fluxo de Caixa Realizado no Período (R$)"));
        painelGrafico.add(new JLabel("Gráfico..."));

        // Painel inferior com botões
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarReceita = new JButton("Adicionar Receita");
        JButton botaoAdicionarDespesa = new JButton("Adicionar Despesa");
        JButton sair = new JButton("Sair");

        painelBotoes.add(botaoAdicionarReceita);
        painelBotoes.add(botaoAdicionarDespesa);
        painelBotoes.add(sair);

        // Adiciona os painéis à janela principal
        add(painelResumo, BorderLayout.NORTH);
        add(painelGrafico, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ação para o botão de adicionar receita
        botaoAdicionarReceita.addActionListener(e -> {
            // Adiciona uma receita e atualiza a tela
            receitas += 500.00;
            atualizarResumo();
        });

        // Ação para o botão de adicionar despesa
        botaoAdicionarDespesa.addActionListener(e -> {
            // Adiciona uma despesa e atualiza a tela
            despesas += 100.00;
            atualizarResumo();
        });

        // Ação para o botão de sair
        sair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
        });
    }
}
