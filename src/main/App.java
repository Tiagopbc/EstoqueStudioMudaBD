package main;

import dao.ProdutoDAO;
import model.Produto;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();

        try {
            // 1. INSERIR PRODUTO
            Produto novo = new Produto(0, "Adubo Orgânico", "Ideal para plantas ornamentais", "Fertilizante", 15);
            dao.inserir(novo);
            System.out.println("Produto inserido com sucesso.");

            // 2. LISTAR PRODUTOS
            List<Produto> produtos = dao.listar();
            System.out.println("Lista de produtos:");
            for (Produto p : produtos) {
                System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getQuantidade() + ")");
            }

            // 3. ATUALIZAR O PRIMEIRO PRODUTO
            if (!produtos.isEmpty()) {
                Produto p = produtos.get(0);
                p.setQuantidade(p.getQuantidade() + 10);
                dao.atualizar(p);
                System.out.println("Produto atualizado.");
            }

            // 4. DELETAR O ÚLTIMO PRODUTO
            if (!produtos.isEmpty()) {
                int idParaDeletar = produtos.get(produtos.size() - 1).getId();
                dao.deletar(idParaDeletar);
                System.out.println("Produto deletado: ID " + idParaDeletar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
