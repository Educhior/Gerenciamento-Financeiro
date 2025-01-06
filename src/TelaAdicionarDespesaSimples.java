import java.awt.*;
import javax.swing.*;

public class TelaAdicionarDespesaSimples extends JFrame {

    private JTextField campoValor, campoCategoria, campoDescricao;
    private JComboBox<String> comboRepeticao, comboConta;
    private JButton botaoSalvar, botaoCancelar;

    public TelaAdicionarDespesaSimples() {
        // Definições básicas da janela
        setTitle("Adicionar Despesas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new GridLayout(6, 2, 10, 10)); // Usando GridLayout simples

        // Inicializando os componentes
        JLabel labelValor = new JLabel("Valor (R$):");
        campoValor = new JTextField();

        JLabel labelDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField();

        JLabel labelCategoria = new JLabel("Categoria:");
        campoCategoria = new JTextField();

        JLabel labelRepeticao = new JLabel("Repetição:");
        comboRepeticao = new JComboBox<>(new String[] {"Única", "Mensal", "Anual"});

        JLabel labelConta = new JLabel("Conta:");
        comboConta = new JComboBox<>(new String[] {"Conta Corrente", "Cartão de Crédito", "Outros"});

        // Botões
        botaoSalvar = new JButton("Salvar Despesa");
        botaoCancelar = new JButton("Cancelar");

        // Adicionando os componentes ao layout
        add(labelValor);
        add(campoValor);

        add(labelDescricao);
        add(campoDescricao);

        add(labelCategoria);
        add(campoCategoria);

        add(labelRepeticao);
        add(comboRepeticao);

        add(labelConta);
        add(comboConta);

        // Adicionando os botões de ação
        add(botaoSalvar);
        add(botaoCancelar);

        // Ações dos botões
        botaoSalvar.addActionListener(e -> salvarDespesa());
        botaoCancelar.addActionListener(e -> dispose());  // Fecha a janela
    }

    private void salvarDespesa() {
        String valorStr = campoValor.getText();
        String descricao = campoDescricao.getText();
        String categoria = campoCategoria.getText();
        String repeticao = (String) comboRepeticao.getSelectedItem();
        String conta = (String) comboConta.getSelectedItem();

        // Exibe mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Despesa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Fecha a janela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarDespesaSimples tela = new TelaAdicionarDespesaSimples();
            tela.setVisible(true);
        });
    }
}
