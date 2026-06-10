import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // Dados de conexão com o banco MySQL

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_agenda"; 
    
    private static final String USER = "root"; 

    private static final String PASSWORD = "sua_senha"; 

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            return conexao;
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}