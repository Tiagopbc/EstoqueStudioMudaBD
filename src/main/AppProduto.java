package main;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class AppProduto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO();
        int opcao;

        do {
            System.out.println("\n===== MENU PRODUTO =====");
            System.out.println("1 - Inserir produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Deletar produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Descrição: ");
                        String descricao = sc.nextLine();
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Quantidade: ");
                        int quantidade = sc.nextInt();
                        sc.nextLine(); // limpar buffer
                        dao.inserir(new Produto(0, nome, descricao, tipo, quantidade));
                        System.out.println("Produto inserido!");
                    }
                    case 2 -> {
                        List<Produto> lista = dao.listar();
                        System.out.println("\nProdutos cadastrados:");
                        for (Produto p : lista) {
                            System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getQuantidade() + ")");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do produto: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Nova descrição: ");
                        String descricao = sc.nextLine();
                        System.out.print("Novo tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Nova quantidade: ");
                        int quantidade = sc.nextInt();
                        sc.nextLine();
                        dao.atualizar(new Produto(id, nome, descricao, tipo, quantidade));
                        System.out.println("Produto atualizado!");
                    }
                    case 4 -> {
                        System.out.print("ID do produto: ");
                        int id = sc.nextInt();
                        dao.deletar(id);
                        System.out.println("Produto deletado!");
                    }
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }
}
