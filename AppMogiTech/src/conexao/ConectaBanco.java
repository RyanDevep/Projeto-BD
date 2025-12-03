/*
 * @author Ryan B. | Camila S.
 */
package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/mogivendas";
    private static final String USER = "root";
    private static final String PASS = "563526"; 

    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }
    }
}