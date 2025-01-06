import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Gerenciador Financeiro - Tela Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior com resumo de dados
        JPanel painelResumo = new JPanel(new GridLayout(1, 4, 10, 10));
        painelResumo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelResumo.add(criarPainelResumo("Caixa Total (R$)", "R$ 15.520,00", new Color(76, 175, 80)));
        painelResumo.add(criarPainelResumo("Despesas (R$)", "R$ 530,00", new Color(244, 67, 54)));
        painelResumo.add(criarPainelResumo("Receita (R$)", "R$ 14.990,00", new Color(33, 150, 243)));
        painelResumo.add(criarPainelResumo("Investimentos (R$)", "R$ 15.120,00", new Color(255, 193, 7)));

        // Placeholder para gráfico
        JPanel painelGrafico = new JPanel();
        painelGrafico.setBorder(BorderFactory.createTitledBorder("Fluxo de Caixa Realizado no Período (R$)"));
        painelGrafico.add(new JLabel("Gráfico..."));

        // Painel inferior com botões
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarReceita = new JButton("Adicionar Receita");
        JButton botaoAdicionarDespesa = new JButton("Adicionar Despesa");
        painelBotoes.add(botaoAdicionarReceita);
        painelBotoes.add(botaoAdicionarDespesa);

        // Adiciona os painéis à janela principal
        add(painelResumo, BorderLayout.NORTH);
        add(painelGrafico, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ação para o botão de adicionar receita
        botaoAdicionarReceita.addActionListener(e -> {
            TelaAdicionarReceitaSimples telaAdicionarReceitaSimples = new TelaAdicionarReceitaSimples();
            telaAdicionarReceitaSimples.setVisible(true);
            
        });

        // Ação para o botão de adicionar despesa
        botaoAdicionarDespesa.addActionListener(e -> {
            TelaAdicionarDespesaSimples telaAdicionarDespesaSimples = new TelaAdicionarDespesaSimples();
            telaAdicionarDespesaSimples.setVisible(true);
        });
    }

    private JPanel criarPainelResumo(String titulo, String valor, Color cor) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(BorderFactory.createLineBorder(cor, 2));

        JLabel labelTitulo = new JLabel(titulo, SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(labelTitulo, BorderLayout.NORTH);

        JLabel labelValor = new JLabel(valor, SwingConstants.CENTER);
        labelValor.setFont(new Font("Arial", Font.BOLD, 20));
        labelValor.setForeground(cor);
        painel.add(labelValor, BorderLayout.CENTER);

        JButton botaoDetalhes = new JButton("Ver detalhes");
        botaoDetalhes.addActionListener(e -> 
            JOptionPane.showMessageDialog(null, "Exibindo detalhes sobre: " + titulo));
        painel.add(botaoDetalhes, BorderLayout.SOUTH);

        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
        });
    }
}