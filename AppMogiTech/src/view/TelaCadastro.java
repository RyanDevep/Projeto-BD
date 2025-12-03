/*
 * @author Ryan B. | Camila S.
 */
package view;

import dao.ProdutoDAO;
import modelo.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaCadastro extends JFrame {

    private JTextField txtNome, txtPreco, txtQuantidade;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private ProdutoDAO dao = new ProdutoDAO();

    public TelaCadastro() {
        setTitle("Cadastro de Produtos");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarComponentes();
        carregarTabela();
    }

    private void criarComponentes() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        painel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        painel.add(txtNome);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 60, 80, 25);
        painel.add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(100, 60, 200, 25);
        painel.add(txtPreco);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 100, 80, 25);
        painel.add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(100, 100, 200, 25);
        painel.add(txtQuantidade);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(350, 20, 120, 30);
        painel.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(350, 60, 120, 30);
        painel.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(350, 100, 120, 30);
        painel.add(btnExcluir);

        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Preço", "Quantidade"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 160, 650, 220);
        painel.add(scroll);

        add(painel);

        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
    }

    private void salvar() {
        Produto p = new Produto(
                txtNome.getText(),
                Double.parseDouble(txtPreco.getText()),
                Integer.parseInt(txtQuantidade.getText())
        );

        if (dao.cadastrar(p)) {
            JOptionPane.showMessageDialog(this, "Cadastrado!");
            carregarTabela();
        }
    }

    private void atualizar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;

        int id = (int) tabela.getValueAt(linha, 0);

        Produto p = new Produto(
                id,
                txtNome.getText(),
                Double.parseDouble(txtPreco.getText()),
                Integer.parseInt(txtQuantidade.getText())
        );

        if (dao.atualizar(p)) {
            JOptionPane.showMessageDialog(this, "Atualizado!");
            carregarTabela();
        }
    }

    private void excluir() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;

        int id = (int) tabela.getValueAt(linha, 0);

        if (dao.excluir(id)) {
            JOptionPane.showMessageDialog(this, "Excluído!");
            carregarTabela();
        }
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        List<Produto> lista = dao.listar();

        for (Produto p : lista) {
            modeloTabela.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getPreco(),
                    p.getQuantidade()
            });
        }
    }

    public static void main(String[] args) {
        new TelaCadastro().setVisible(true);
    }
}
