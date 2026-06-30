package agenda.telas;

import agenda.modelo.Cidade;
import agenda.servico.ServicoCidade;
import javax.swing.*;
import java.awt.*;

public class TelaCidade extends JFrame {

    private JTextField txtNome;

    public TelaCidade() {
        setTitle("Cadastro de Cidade");
        setSize(300, 150);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Nome da Cidade:"));
        txtNome = new JTextField();
        add(txtNome);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCidade());
        add(btnSalvar);

        JButton btnListar = new JButton("Listar no Console");
        btnListar.addActionListener(e -> listarCidades());
        add(btnListar);
    }

    private void salvarCidade() {
        try {
            String nome = txtNome.getText();
            if (nome.isEmpty()) throw new Exception("O nome da cidade não pode ser vazio.");
            
            
            ServicoCidade.criarCidade(nome);
            JOptionPane.showMessageDialog(this, "Cidade salva com sucesso!");
            txtNome.setText(""); // Limpa o campo
            
        } catch (Exception ex) {
           
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void listarCidades() {
        
        System.out.println("--- BUSCA DE CIDADE ---");
        try {
            String busca = JOptionPane.showInputDialog("Digite o nome da cidade para buscar:");
            if(busca != null) {
                Cidade c = ServicoCidade.localizarCidade(busca);
                if(c != null) {
                    JOptionPane.showMessageDialog(this, "Encontrada: " + c.getNome() + " (ID: " + c.getId() + ")");
                } else {
                    JOptionPane.showMessageDialog(this, "Cidade não encontrada.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}