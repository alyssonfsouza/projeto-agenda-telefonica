import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    // 1. A URL agora usa 'mysql' e a porta '3306'
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_agenda"; 
    
    // 2. O usuário padrão do MySQL é 'root'
    private static final String USER = "root"; 
    
    // 3. Aqui fica a mesma senha que você digita para abrir o HeidiSQL
    private static final String PASSWORD = "senha"; 

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