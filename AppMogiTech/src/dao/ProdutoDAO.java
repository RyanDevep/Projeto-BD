/*
 * @author Ryan B. | Camila S.
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import conexao.ConectaBanco;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Produto;
import java.sql.*;

public class ProdutoDAO {

    public boolean cadastrar(Produto p) {
        String sql = "INSERT INTO produtos(nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection con = ConectaBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());

            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM produtos";
        List<Produto> lista = new ArrayList<>();

        try (Connection con = ConectaBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome=?, preco=?, quantidade=? WHERE id=?";

        try (Connection con = ConectaBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection con = ConectaBanco.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
