import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
public class TelaAdicionarReceita extends JFrame {
    private JFormattedTextField campoValor;
    private JTextField campoDescricao, campoCategoria;
    private JButton botaoSalvar, botaoCancelar;
    public TelaAdicionarReceita() {
        // Definições básicas da janela
        setTitle("Adicionar Receita");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel central
        JPanel painelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Configurando o campo de valor com máscara numérica
        JLabel labelValor = new JLabel("Valor (R$):");
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        campoValor = new JFormattedTextField(formatter);
        campoValor.setColumns(10);
        JLabel labelDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField(20);
        JLabel labelCategoria = new JLabel("Categoria:");
        campoCategoria = new JTextField(20);

        // Adicionando componentes ao painel central
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(labelValor, gbc);
        gbc.gridx = 1;
        painelCentral.add(campoValor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(labelDescricao, gbc);
        gbc.gridx = 1;
        painelCentral.add(campoDescricao, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(labelCategoria, gbc);
        gbc.gridx = 1;
        painelCentral.add(campoCategoria, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoSalvar = new JButton("Salvar Receita");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        // Adicionando painéis à janela
        add(painelCentral, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        botaoSalvar.addActionListener(e -> salvarReceita());
        botaoCancelar.addActionListener(e -> dispose());
    }
    private void salvarReceita() {
        try {

            // Captura e validação de campos
            String valorTexto = campoValor.getText();
            String descricao = campoDescricao.getText().trim();
            String categoria = campoCategoria.getText().trim();
            if (valorTexto.isEmpty() || descricao.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Convertendo valor para número
            double valor = ((Number) campoValor.getValue()).doubleValue();
            // Gerando data e hora atual
            String dataHoraAtual = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            
            // Criando a receita
            int idReceita = GerenciadorReceitasCSV.getProximoIdReceita();
            Receita receita = new Receita(idReceita, valor, descricao, categoria, dataHoraAtual);
            // Salvar receita no arquivo CSV
            GerenciadorReceitasCSV.salvarReceitas(java.util.List.of(receita));
            JOptionPane.showMessageDialog(this, "Receita salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a janela após salvar
        } catch (IOException e) {
            Logger.getLogger(TelaAdicionarDespesa.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Erro ao salvar a despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarReceita tela = new TelaAdicionarReceita();
            tela.setVisible(true);
        });
    }
}