package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Locacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LocacaoDAO extends GenericDAO {
    public LocacaoDAO() {
        super();
    }

    public ArrayList<Locacao> readAllByCliente(int clienteId) throws RuntimeException
    {
        ArrayList<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacao WHERE cliente_id = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int locadoraId = rs.getInt("locadora_id");
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

}
