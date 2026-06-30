package agenda.telas;

import agenda.servico.Servico;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Menu Principal - Agenda");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        JLabel lblTitulo = new JLabel("Escolha uma opção de gerenciamento:");
        lblTitulo.setBounds(80, 20, 300, 25);
        add(lblTitulo);

        JButton btnPessoal = new JButton("Contatos Pessoais");
        btnPessoal.setBounds(100, 60, 180, 40);
        btnPessoal.addActionListener(e -> new TelaContatoPessoal().setVisible(true));
        add(btnPessoal);

        JButton btnComercial = new JButton("Contatos Comerciais");
        btnComercial.setBounds(100, 120, 180, 40);
        btnComercial.addActionListener(e -> new TelaContatoComercial().setVisible(true));
        add(btnComercial);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Servico.finalizar();
            }
        });
    }
}