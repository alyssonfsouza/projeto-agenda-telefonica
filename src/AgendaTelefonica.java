import java.sql.*;

public class AgendaTelefonica {

    // 1. MÉTODO PARA ADICIONAR (Create)
    public void adicionarContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.executeUpdate();
            System.out.println();
            System.out.println("Contato adicionado com sucesso!");
            System.out.println();
            
        } catch (SQLException e) {
            System.err.println();
            System.err.println("Erro ao adicionar contato: " + e.getMessage());
            System.out.println();
        }
    }

    // 2. MÉTODO PARA LISTAR TUDO (Read)
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
            System.out.println();
            System.err.println("Erro ao listar: " + e.getMessage());
        }
    }

    // 3. MÉTODO PARA BUSCAR POR NOME (Read) - Usando LIKE do MySQL e retornando boolean
    public boolean buscarContato(String nome) {
        String sql = "SELECT * FROM contatos WHERE nome LIKE ?"; 
        boolean encontrou = false;
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("[" + rs.getInt("id") + "] Nome: " + rs.getString("nome") + " - Tel: " + rs.getString("telefone"));
                encontrou = true;
                System.out.println();
            }
            if (!encontrou) {
                System.out.println("Nenhum contato encontrado com o nome: " + nome);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro na busca: " + e.getMessage());
            System.out.println();
        }
        return encontrou;
    }

    // 4. MÉTODO PARA REMOVER (Delete)
    public void removerContato(String nome) {
        String sql = "DELETE FROM contatos WHERE nome = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Contato removido!");
                System.out.println();
            } else {
                System.out.println("Contato não encontrado para remoção.");
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover: " + e.getMessage());
            System.out.println();
        }
    }

    // Método para verificar se um contato já existe no banco
    public boolean existeContato(String nome) {
        String sql = "SELECT COUNT(*) FROM contatos WHERE nome = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                // Se o contador for maior que 0, o nome já existe
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar nome: " + e.getMessage());
        }
        return false;
    }

    // Método para atualizar os dados no banco de dados buscando pelo ID
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