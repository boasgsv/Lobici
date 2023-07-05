package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO extends GenericDAO {
    public LocacaoDAO() {
        super();
    }


    public Locacao find(long id) {
        Locacao locacao = null;
        String query = "SELECT * FROM locacao WHERE id = ?";

        try {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long clienteId = rs.getLong("cliente_id");
                long locadoraId = rs.getLong("locadora_id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime datahora = LocalDateTime.parse(rs.getString("datahora"), formatter);
                locacao = new Locacao(id, datahora, clienteId, locadoraId);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacao;
    }
    public List<Locacao> readAllByCliente(long clienteId) throws RuntimeException
    {
        ArrayList<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacao WHERE cliente_id = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, clienteId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                long locadoraId = rs.getLong("locadora_id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime datahora = LocalDateTime.parse(rs.getString("datahora"), formatter);
                Locacao locacao = new Locacao(id, datahora, clienteId, locadoraId);
                locacoes.add(locacao);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return locacoes;
    }

    public List<Locacao> readAllByLocadora(long locadoraId) throws RuntimeException
    {
        ArrayList<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacao WHERE locadora_id = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, locadoraId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                long clienteId = rs.getLong("cliente_id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime datahora = LocalDateTime.parse(rs.getString("datahora"), formatter);
                Locacao locacao = new Locacao(id, datahora, clienteId, locadoraId);
                locacoes.add(locacao);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return locacoes;
    }

    public void insert(long clienteId, long locadoraId, LocalDateTime ldt) {
        String query = "INSERT INTO locacao (cliente_id, locadora_id, datahora) VALUES (?, ?, ?)";

        try  {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = ldt.format(formatter);

            stmt.setLong(1, clienteId);
            stmt.setLong(2, locadoraId);
            stmt.setString(3, formatDateTime);

            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Locacao findByClienteAndDateTime(long clienteId, LocalDateTime ldt) {
        Locacao locacao = null;
        String query = "SELECT * FROM locacao WHERE cliente_id = ? AND datahora = ?";

        try {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setLong(1, clienteId);
            stmt.setString(2, ldt.format(formatter));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long id = rs.getLong("id");
                long locadoraId = rs.getLong("locadora_id");
                locacao = new Locacao(id, ldt, clienteId, locadoraId);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacao;
    }

    public Locacao findByLocadoraAndDateTime(long locadoraId, LocalDateTime ldt) {
        Locacao locacao = null;
        String query = "SELECT * FROM locacao WHERE locadora_id = ? AND datahora = ?";

        try {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setLong(1, locadoraId);
            stmt.setString(2, ldt.format(formatter));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long id= rs.getLong("id");
                long clienteId = rs.getLong("cliente_id");
                locacao = new Locacao(id, ldt, clienteId, locadoraId);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacao;
    }
}