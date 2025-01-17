import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaLogin extends JFrame {
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JButton botaoEsqueciSenha;

    public TelaLogin() {
        setTitle("Login - Gerenciador Financeiro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Painel Superior
        JPanel painelSuperior = new JPanel();
        JLabel titulo = new JLabel("Gerenciador Financeiro");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelSuperior.add(titulo);

        // Painel Central (Formulário)
        JPanel painelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        painelCentral.add(new JLabel("E-mail:"));
        campoEmail = new JTextField();
        painelCentral.add(campoEmail);

        painelCentral.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painelCentral.add(campoSenha);

        // Botões
        botaoLogin = new JButton("Login");
        botaoLogin.setBackground(new Color(0, 123, 255));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        painelCentral.add(botaoLogin);

        botaoEsqueciSenha = new JButton("Esqueci Minha Senha");
        botaoEsqueciSenha.setBackground(new Color(220, 53, 69));
        botaoEsqueciSenha.setForeground(Color.WHITE);
        botaoEsqueciSenha.setFocusPainted(false);
        painelCentral.add(botaoEsqueciSenha);

        // Painel Inferior (Footer)
        JPanel painelInferior = new JPanel();
        JLabel footer = new JLabel("© 2024 - Gerenciador Financeiro");
        footer.setFont(new Font("Arial", Font.ITALIC, 12));
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        painelInferior.add(footer);

        // Adicionando os painéis ao Frame
        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // Ações dos botões
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());
                if (email.equals("admin") && senha.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    TelaPrincipal telaPrincipal = new TelaPrincipal();
                    telaPrincipal.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoEsqueciSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Entre em contato com o suporte para redefinir sua senha.");
            }
        });
    }

    public static void main(String[] args) {
        // Inicializa a aplicação
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
