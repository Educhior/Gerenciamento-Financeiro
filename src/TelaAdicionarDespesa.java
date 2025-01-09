import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
public class TelaAdicionarDespesa extends JFrame {
    private final JFormattedTextField campoValor;
    private final JTextField campoCategoria, campoDescricao;
    private final JButton botaoSalvar, botaoCancelar;
    public TelaAdicionarDespesa() {
        // Definições básicas da janela
        setTitle("Adicionar Despesas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new GridLayout(6, 2, 10, 10)); // Usando GridLayout simples
        // Inicializando os componentes
        JLabel labelValor = new JLabel("Valor (R$):");
        
        // Configurando o campo de valor com máscara numérica
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        campoValor = new JFormattedTextField(formatter);
        campoValor.setColumns(10);
        JLabel labelDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField();
        JLabel labelCategoria = new JLabel("Categoria:");
        campoCategoria = new JTextField();
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
        // Adicionando os botões de ação
        add(botaoSalvar);
        add(botaoCancelar);
        // Ações dos botões
        botaoSalvar.addActionListener(e -> salvarDespesa());
        botaoCancelar.addActionListener(e -> dispose());  // Fecha a janela
    }
    private void salvarDespesa() {
        try {
            // Captura e validação de campos
            String valorStr = campoValor.getText();
            String descricao = campoDescricao.getText().trim();
            String categoria = campoCategoria.getText().trim();
            if (valorStr.isEmpty() || descricao.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Convertendo valor para número
            double valor = ((Number) campoValor.getValue()).doubleValue();
            // Gerando data e hora atual
            String dataHoraAtual = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            // Criando a despesa (gerando um id sequencial, por exemplo)
            int idDespesa = GerenciadorDespesasCSV.getProximoIdDespesa();
            Despesa despesa = new Despesa(idDespesa, valor, descricao, categoria, dataHoraAtual);
            // Salvar despesa no arquivo CSV
            GerenciadorDespesasCSV.salvarDespesas(java.util.List.of(despesa));
            JOptionPane.showMessageDialog(this, "Despesa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a janela após salvar
        } catch (IOException e) {
            Logger.getLogger(TelaAdicionarDespesa.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Erro ao salvar a despesa.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAdicionarDespesa tela = new TelaAdicionarDespesa();
            tela.setVisible(true);
        });
    }
}