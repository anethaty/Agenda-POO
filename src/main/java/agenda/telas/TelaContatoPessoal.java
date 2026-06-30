package agenda.telas;

import agenda.modelo.Cidade;
import agenda.modelo.ContatoPessoal;
import agenda.servico.ServicoCidade;
import agenda.servico.ServicoContato;
import agenda.servico.ServicoContatoPessoal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaContatoPessoal extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField txtNome, txtGrau, txtNomeCidade;
    private JLabel lblStatusCidade;
    private int idCidadeSelecionada = -1; 

    public TelaContatoPessoal() {
        setTitle("Gestão de Contatos Pessoais");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null); 

        // --- TABELA ---
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Grau", "Cidade", "Telefones"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 640, 200);
        add(scroll);

        // --- CAMPOS DO CONTATO ---
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 240, 50, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(70, 240, 190, 25);
        add(txtNome);

        JLabel lblGrau = new JLabel("Grau (1,2,3):");
        lblGrau.setBounds(280, 240, 80, 25);
        add(lblGrau);

        txtGrau = new JTextField();
        txtGrau.setBounds(370, 240, 50, 25);
        add(txtGrau);

        // --- CAMPOS DE SELEÇÃO/CRIAÇÃO DE CIDADE ---
        JLabel lblCidade = new JLabel("Nome da Cidade:");
        lblCidade.setBounds(20, 280, 120, 25);
        add(lblCidade);

        txtNomeCidade = new JTextField();
        txtNomeCidade.setBounds(130, 280, 130, 25);
        add(txtNomeCidade);

        JButton btnBuscarCidade = new JButton("Buscar Cidade");
        btnBuscarCidade.setBounds(270, 280, 130, 25);
        btnBuscarCidade.addActionListener(e -> buscarCidade());
        add(btnBuscarCidade);

        JButton btnCriarCidade = new JButton("Criar Cidade");
        btnCriarCidade.setBounds(410, 280, 120, 25);
        btnCriarCidade.addActionListener(e -> criarCidade());
        add(btnCriarCidade);

        lblStatusCidade = new JLabel("Nenhuma cidade selecionada.");
        lblStatusCidade.setBounds(20, 310, 400, 25);
        lblStatusCidade.setForeground(java.awt.Color.BLUE);
        add(lblStatusCidade);

        // --- BOTÕES DE AÇÃO DO CONTATO ---
        JButton btnCriar = new JButton("Criar Contato");
        btnCriar.setBounds(20, 350, 120, 30);
        btnCriar.addActionListener(e -> criarContato());
        add(btnCriar);

        JButton btnAlterar = new JButton("Alterar Selecionado");
        btnAlterar.setBounds(150, 350, 150, 30);
        btnAlterar.addActionListener(e -> alterarContato());
        add(btnAlterar);

        JButton btnApagar = new JButton("Apagar");
        btnApagar.setBounds(310, 350, 100, 30);
        btnApagar.addActionListener(e -> apagarContato());
        add(btnApagar);

        JButton btnTelefone = new JButton("Adicionar Telefone");
        btnTelefone.setBounds(420, 350, 150, 30);
        btnTelefone.addActionListener(e -> adicionarTelefone());
        add(btnTelefone);

        carregarTabela();

        // Facilita preencher os campos ao clicar na tabela
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (tabela.getSelectedRow() != -1) {
                txtNome.setText((String) modeloTabela.getValueAt(tabela.getSelectedRow(), 1));
                txtGrau.setText(modeloTabela.getValueAt(tabela.getSelectedRow(), 2).toString());
                txtNomeCidade.setText((String) modeloTabela.getValueAt(tabela.getSelectedRow(), 3));
                buscarCidade(); // Auto-seleciona a cidade
            }
        });
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        try {
            List<ContatoPessoal> contatos = ServicoContatoPessoal.listarContatosPessoais();
            for (ContatoPessoal cp : contatos) {
                String telefones = String.join(", ", cp.getTelefones());
                modeloTabela.addRow(new Object[]{
                    cp.getId(), cp.getNome(), cp.getGrauProximidade(), cp.getCidade().getNome(), telefones
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarCidade() {
        String nome = txtNomeCidade.getText().trim();
        Cidade cid = ServicoCidade.localizarCidade(nome);
        if (cid != null) {
            idCidadeSelecionada = cid.getId();
            lblStatusCidade.setText("Pronto! Cidade vinculada: " + cid.getNome() + " (ID: " + cid.getId() + ")");
        } else {
            idCidadeSelecionada = -1;
            lblStatusCidade.setText("Cidade não encontrada. Clique em 'Criar Cidade' para cadastrar.");
        }
    }

    private void criarCidade() {
        try {
            String nome = txtNomeCidade.getText().trim();
            ServicoCidade.criarCidade(nome);
            buscarCidade(); // Busca imediatamente a recém-criada para pegar o ID
            JOptionPane.showMessageDialog(this, "Cidade criada e selecionada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void criarContato() {
        try {
            if (idCidadeSelecionada == -1) throw new Exception("Busque e selecione uma cidade primeiro.");
            int grau = Integer.parseInt(txtGrau.getText());
            
            ServicoContatoPessoal.criarContatoPessoal(txtNome.getText(), grau, idCidadeSelecionada);
            JOptionPane.showMessageDialog(this, "Criado com sucesso!");
            carregarTabela();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O grau deve ser um número.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void alterarContato() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha == -1) throw new Exception("Selecione um contato na tabela.");
            int id = (int) tabela.getValueAt(linha, 0);
            
            if (idCidadeSelecionada == -1) throw new Exception("Busque e selecione uma cidade primeiro.");
            int grau = Integer.parseInt(txtGrau.getText());

            ServicoContatoPessoal.alterarContatoPessoal(id, txtNome.getText(), grau, idCidadeSelecionada);
            JOptionPane.showMessageDialog(this, "Alterado com sucesso!");
            carregarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void apagarContato() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha == -1) throw new Exception("Selecione um contato na tabela.");
            int id = (int) tabela.getValueAt(linha, 0);

            ServicoContato.apagarContato(id);
            JOptionPane.showMessageDialog(this, "Apagado!");
            carregarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void adicionarTelefone() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha == -1) throw new Exception("Selecione um contato na tabela.");
            int id = (int) tabela.getValueAt(linha, 0);

            String tel = JOptionPane.showInputDialog(this, "Digite o novo telefone:");
            if(tel != null && !tel.isEmpty()) {
                ServicoContato.adicionarTelefoneContato(tel, id);
                carregarTabela();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}