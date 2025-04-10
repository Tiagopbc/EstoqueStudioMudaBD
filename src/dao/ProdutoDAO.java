package dao;

import conexao.Conexao;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao, tipo, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getTipo());
            stmt.setInt(4, produto.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setTipo(rs.getString("tipo"));
                p.setQuantidade(rs.getInt("quantidade"));
                lista.add(p);
            }
        }
        return lista;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, tipo = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getTipo());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
