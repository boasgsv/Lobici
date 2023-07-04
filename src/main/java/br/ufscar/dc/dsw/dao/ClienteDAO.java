package br.ufscar.dc.dsw.dao;
import br.ufscar.dc.dsw.domain.Cliente;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO extends GenericDAO {
    public Cliente find(long userId) {
        return buscarClientePorId(userId);
    }

    public void adicionarCliente(Cliente cliente) {
        String query = "INSERT INTO cliente (usuario_id, CPF, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setLong(1, cliente.getId());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSexo());
    
            java.util.Date dataUtil = cliente.getDataNascimento();
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
            stmt.setDate(6, dataSql);
    
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarClientePorId(Long id) {
        String query = "SELECT * FROM cliente WHERE usuario_id = ?";
        Cliente cliente = null;

        try (Connection conexao = getConnection();
             PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long Id = rs.getInt("usuario_id");
                String CPF = rs.getString("CPF");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String sexo = rs.getString("sexo");
                Date dataNascimento = rs.getDate("data_nascimento");

                cliente = new Cliente(Id,CPF, nome, telefone, sexo, dataNascimento);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                
                // String email = resultSet.getString("email");
                // String password = resultSet.getString("password");
                long Id = resultSet.getInt("usuario_id");
                String CPF = resultSet.getString("CPF");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                Date dataNascimento = resultSet.getDate("data_nascimento");
        
                Cliente cliente = new Cliente(Id,CPF, nome, telefone, sexo, dataNascimento);
                listaClientes.add(cliente);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print(listaClientes);
        return listaClientes;
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        String query = "UPDATE cliente SET nome = ?, telefone = ?, sexo = ?, data_nascimento = ?, CPF = ? WHERE usuario_id = ?";
    
        try {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);

            stmt.setString(1, clienteAtualizado.getNome());
            stmt.setString(2, clienteAtualizado.getTelefone());
            stmt.setString(3, clienteAtualizado.getSexo());
            stmt.setDate(4, new java.sql.Date(clienteAtualizado.getDataNascimento().getTime()));
            stmt.setString(5, clienteAtualizado.getCPF());
            stmt.setLong(6, clienteAtualizado.getId());

            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
    public void delete(Long id) {
        String sql = "DELETE FROM cliente where usuario_id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void excluirLocacoesPorCliente(Long id) {
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM locacao WHERE cliente_id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir locações do cliente", e);
        }
    }
}
