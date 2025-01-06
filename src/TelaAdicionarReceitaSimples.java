import javax.swing.*;
import java.awt.*;

public class TelaAdicionarReceitaSimples extends JFrame {

    private JTextField campoValor, campoDescricao, campoCategoria;
    private JComboBox<String> comboRepeticao, comboConta;
    private JButton botaoSalvar, botaoCancelar;

    public TelaAdicionarReceitaSimples() {
        // Definições básicas da janela
        setTitle("Adicionar Receita");
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
        botaoSalvar = new JButton("Salvar Receita");
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
        botaoSalvar.addActionListener(e -> salvarReceita());
        botaoCancelar.addActionListener(e -> dispose());
    }

    private void salvarReceita() {
        // Exemplo de mensagem simples de sucesso
        String valor = campoValor.getText();
        String descricao = campoDescricao.getText();
        String categoria = campoCategoria.getText();
        String repeticao = (String) comboRepeticao.getSelectedItem();
        String conta = (String) comboConta.getSelectedItem();

        if (valor.isEmpty() || descricao.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Receita salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a janela após salvar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarReceitaSimples tela = new TelaAdicionarReceitaSimples();
            tela.setVisible(true);
        });
    }
}
