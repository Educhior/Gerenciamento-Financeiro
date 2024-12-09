import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;  // Importação da classe JDateChooser

public class TelaAdicionarDespesa extends JFrame {

    private JTextField campoValor;
    private JTextField campoCategoria;
    private JTextField campoDescricao;
    private JTextField campoObservacoes;
    private JTextField campoTags;
    private JComboBox<String> comboRepeticao;
    private JComboBox<String> comboConta;
    private JDateChooser campoData;

    public TelaAdicionarDespesa() {
        setTitle("Adicionar Despesa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        JLabel labelValor = new JLabel("Valor (R$):");
        campoValor = new JTextField();

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
        JButton botaoSalvar = new JButton("Salvar Despesa");
        JButton botaoCancelar = new JButton("Cancelar");

        // Adicionando os componentes ao layout
        add(labelValor);
        add(campoValor);

        add(labelDescricao);
        add(campoDescricao);

        add(labelCategoria);
        add(campoCategoria);

        add(labelData);
        add(campoData);

        add(labelRepeticao);
        add(comboRepeticao);

        add(labelConta);
        add(comboConta);

        add(labelObservacoes);
        add(campoObservacoes);

        add(labelTags);
        add(campoTags);

        add(botaoSalvar);
        add(botaoCancelar);

        // Ações dos botões
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDespesa();
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de adicionar despesa
            }
        });
    }

    private void salvarDespesa() {
        String valor = campoValor.getText();
        String descricao = campoDescricao.getText();
        String categoria = campoCategoria.getText();
        Date data = campoData.getDate();
        String repeticao = (String) comboRepeticao.getSelectedItem();
        String conta = (String) comboConta.getSelectedItem();
        String observacoes = campoObservacoes.getText();
        String tags = campoTags.getText();

        // Validação simples
        if (valor.isEmpty() || descricao.isEmpty() || categoria.isEmpty() || data == null || conta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Aqui você pode adicionar lógica para salvar os dados no banco de dados ou em um arquivo
            JOptionPane.showMessageDialog(this, "Despesa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a janela após salvar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarDespesa tela = new TelaAdicionarDespesa();
            tela.setVisible(true);
        });
    }
}
