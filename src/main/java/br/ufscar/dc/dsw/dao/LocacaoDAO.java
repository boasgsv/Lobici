package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Locacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO extends GenericDAO {
    public LocacaoDAO() {
        super();
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

    public void insert(long locadoraId, long clienteId, LocalDateTime ldt) {
        String query = "INSERT INTO locacao (cliente_id, locadora_id, datahora) VALUES (?, ?, ?)";

        try  {
            Connection conexao = getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = ldt.format(formatter);

            stmt.setLong(1, locadoraId);
            stmt.setLong(2, clienteId);
            stmt.setString(3, formatDateTime);

            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}