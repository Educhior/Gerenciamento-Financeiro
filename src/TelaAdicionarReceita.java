import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class TelaAdicionarReceita extends JFrame {

    private JTextField campoValor, campoCategoria, campoDescricao, campoObservacoes, campoTags;
    private JComboBox<String> comboRepeticao, comboConta;
    private JDateChooser campoData;

    public TelaAdicionarReceita() {
        setTitle("Adicionar Receita");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialização
        JLabel labelValor = new JLabel("Valor (R$):");
        campoValor = new JTextField();
        campoValor.setPreferredSize(new Dimension(200, 25));

        JLabel labelDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField();

        JLabel labelCategoria = new JLabel("Categoria:");
        campoCategoria = new JTextField();

        JLabel labelData = new JLabel("Data:");
        campoData = new JDateChooser();
        campoData.setDateFormatString("dd/MM/yyyy");

        JLabel labelRepeticao = new JLabel("Repetição:");
        comboRepeticao = new JComboBox<>(new String[] {"Única", "Mensal", "Anual"});

        JLabel labelConta = new JLabel("Conta:");
        comboConta = new JComboBox<>(new String[] {"Conta Corrente", "Cartão de Crédito", "Outros"});

        JLabel labelObservacoes = new JLabel("Observações:");
        campoObservacoes = new JTextField();

        JLabel labelTags = new JLabel("Tags:");
        campoTags = new JTextField();

        // Botões
        JButton botaoSalvar = new JButton("Salvar Receita");
        JButton botaoCancelar = new JButton("Cancelar");

        // Adicionando os componentes
        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; add(labelValor, gbc);
        gbc.gridx = 1; add(campoValor, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelDescricao, gbc);
        gbc.gridx = 1; add(campoDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelCategoria, gbc);
        gbc.gridx = 1; add(campoCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelData, gbc);
        gbc.gridx = 1; add(campoData, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelRepeticao, gbc);
        gbc.gridx = 1; add(comboRepeticao, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelConta, gbc);
        gbc.gridx = 1; add(comboConta, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelObservacoes, gbc);
        gbc.gridx = 1; add(campoObservacoes, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; add(labelTags, gbc);
        gbc.gridx = 1; add(campoTags, gbc);

        // Botões centralizados
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = ++row; gbc.anchor = GridBagConstraints.CENTER;
        add(botaoSalvar, gbc);

        gbc.gridy = ++row;
        add(botaoCancelar, gbc);

        // Ações dos botões
        botaoSalvar.addActionListener(e -> salvarReceita());
        botaoCancelar.addActionListener(e -> dispose());
    }

    private void salvarReceita() {
        try {
            String valorStr = campoValor.getText();
            double valor = Double.parseDouble(valorStr);
            String descricao = campoDescricao.getText();
            String categoria = campoCategoria.getText();
            Date data = campoData.getDate();
            String repeticao = (String) comboRepeticao.getSelectedItem();
            String conta = (String) comboConta.getSelectedItem();
            String observacoes = campoObservacoes.getText();
            String tags = campoTags.getText();

            if (valor <= 0 || descricao.isEmpty() || categoria.isEmpty() || data == null || conta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Receita salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo 'Valor (R$)' deve conter um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarReceita tela = new TelaAdicionarReceita();
            tela.setVisible(true);
        });
    }
}
