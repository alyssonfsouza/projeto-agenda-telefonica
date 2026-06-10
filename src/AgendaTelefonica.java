import java.sql.*;

public class AgendaTelefonica {

    // Adiciona um novo contato ao banco de dados
    public void adicionarContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.executeUpdate();
            
            System.out.println("\nContato adicionado com sucesso!\n");
            
        } catch (SQLException e) {
            System.err.println("\nErro ao adicionar contato: " + e.getMessage() + "\n");
        }
    }

    // Lista todos os contatos cadastrados
    public void listarContatos() {
        String sql = "SELECT * FROM contatos ORDER BY nome";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- LISTA DE CONTATOS ---");
            while (rs.next()) {
                System.out.println("[ID: " + rs.getInt("id") + "] Nome: " + rs.getString("nome") + 
                                 " | Tel: " + rs.getString("telefone") + 
                                 " | Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao listar: " + e.getMessage());
        }
    }

    // Busca contatos por nome e retorna true se encontrar
    public boolean buscarContato(String nome) {
        String sql = "SELECT * FROM contatos WHERE nome LIKE ?"; 
        boolean encontrou = false;
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("[ID: " + rs.getInt("id") + "] Nome: " + rs.getString("nome") + " - Tel: " + rs.getString("telefone"));
                encontrou = true;
            }
            if (!encontrou) {
                System.out.println("Nenhum contato encontrado com o nome: " + nome);
            }
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Erro na busca: " + e.getMessage() + "\n");
        }
        return encontrou;
    }

    // Remove um contato usando o nome
    public void removerContato(String nome) {
        String sql = "DELETE FROM contatos WHERE nome = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Contato removido!\n");
            } else {
                System.out.println("Contato não encontrado para remoção.\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover: " + e.getMessage() + "\n");
        }
    }

    // Valida se o contato já está registrado
    public boolean existeContato(String nome) {
        String sql = "SELECT COUNT(*) FROM contatos WHERE nome = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar nome: " + e.getMessage());
        }
        return false;
    }

    // Atualiza os dados de um contato pelo ID
    public void atualizarContato(int id, Contato novoContato) {
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?";
    
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, novoContato.getNome());
            stmt.setString(2, novoContato.getTelefone());
            stmt.setString(3, novoContato.getEmail());
            stmt.setInt(4, id);
        
            int linhasAfetadas = stmt.executeUpdate();
        
            if (linhasAfetadas > 0) {
                System.out.println("Contato atualizado com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado com este ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar contato: " + e.getMessage());
        }
    }
}