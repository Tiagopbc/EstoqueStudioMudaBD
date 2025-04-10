package dao;

import conexao.Conexao;
import model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (data_requisicao, data_entrega, itens, cliente_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getDataRequisicao());
            stmt.setString(2, pedido.getDataEntrega());
            stmt.setString(3, pedido.getItens());
            stmt.setInt(4, pedido.getClienteId());
            stmt.executeUpdate();
        }
    }

    public List<Pedido> listar() throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setDataRequisicao(rs.getString("data_requisicao"));
                p.setDataEntrega(rs.getString("data_entrega"));
                p.setItens(rs.getString("itens"));
                p.setClienteId(rs.getInt("cliente_id"));
                lista.add(p);
            }
        }
        return lista;
    }

    public void atualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedido SET data_requisicao = ?, data_entrega = ?, itens = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getDataRequisicao());
            stmt.setString(2, pedido.getDataEntrega());
            stmt.setString(3, pedido.getItens());
            stmt.setInt(4, pedido.getClienteId());
            stmt.setInt(5, pedido.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
