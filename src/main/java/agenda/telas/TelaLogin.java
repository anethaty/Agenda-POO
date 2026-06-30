package agenda.telas;

import agenda.servico.Servico;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public TelaLogin() {
        setTitle("Login - Agenda");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        try {
            Servico.inicializar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro de Banco: " + e.getMessage());
        }

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(40, 30, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 30, 140, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(40, 70, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 70, 140, 25);
        add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 110, 100, 30);
        add(btnEntrar);

        btnEntrar.addActionListener((ActionEvent e) -> {
            String user = txtUsuario.getText();
            String pass = new String(txtSenha.getPassword());

            //  usuario="seunome" e senha="suasenha"
            if (user.equals("seunome") && pass.equals("suasenha")) {
                new TelaPrincipal().setVisible(true);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}