package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public class LocadoraDAO extends GenericDAO{
    public List<Locadora> getAll() {
        List<Locadora> listaLocadoras = new ArrayList<>();

        String sql = "SELECT * from locadora";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("usuario_id");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                Locadora locadora = new Locadora(id, nome, cnpj, cidade);
                listaLocadoras.add(locadora);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocadoras;
    }

    public Locadora get(int id) {
        Locadora locadora = null;

        String sql = "SELECT * from locadora where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                locadora = new Locadora(id, nome, cnpj, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }

    public void insert(Locadora locadora) {

        String sql = "INSERT INTO locadora (id, CNPJ, nome, cidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, locadora.getId());
            statement.setString(2, locadora.getCNPJ());
            statement.setString(3, locadora.getNome());
            statement.setString(4, locadora.getCidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Locadora locadora) {
        String sql = "DELETE FROM locadora where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locadora locadora) {
        String sql = "UPDATE locadora SET id = ?, CNPJ = ?, nome = ?, cidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, locadora.getId());
            statement.setString(2, locadora.getCNPJ());
            statement.setString(3, locadora.getNome());
            statement.setString(4, locadora.getCidade());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
