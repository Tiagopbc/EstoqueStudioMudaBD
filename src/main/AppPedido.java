package main;

import dao.PedidoDAO;
import model.Pedido;

import java.util.List;
import java.util.Scanner;

public class AppPedido {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoDAO dao = new PedidoDAO();
        int opcao;

        do {
            System.out.println("\n===== MENU PEDIDO =====");
            System.out.println("1 - Inserir pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Atualizar pedido");
            System.out.println("4 - Deletar pedido");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt(); sc.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Data de requisição (YYYY-MM-DD): ");
                        String dataReq = sc.nextLine();
                        System.out.print("Data de entrega (YYYY-MM-DD): ");
                        String dataEnt = sc.nextLine();
                        System.out.print("Itens (ex: Produto1,Produto2): ");
                        String itens = sc.nextLine();
                        System.out.print("ID do cliente: ");
                        int clienteId = sc.nextInt(); sc.nextLine();

                        dao.inserir(new Pedido(0, dataReq, dataEnt, itens, clienteId));
                        System.out.println("Pedido inserido!");
                    }
                    case 2 -> {
                        List<Pedido> lista = dao.listar();
                        System.out.println("\nPedidos cadastrados:");
                        for (Pedido p : lista) {
                            System.out.println(p.getId() + " - Cliente ID: " + p.getClienteId() + " - Itens: " + p.getItens());
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do pedido: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nova data de requisição: ");
                        String dataReq = sc.nextLine();
                        System.out.print("Nova data de entrega: ");
                        String dataEnt = sc.nextLine();
                        System.out.print("Novos itens: ");
                        String itens = sc.nextLine();
                        System.out.print("Novo ID do cliente: ");
                        int clienteId = sc.nextInt(); sc.nextLine();

                        dao.atualizar(new Pedido(id, dataReq, dataEnt, itens, clienteId));
                        System.out.println("Pedido atualizado!");
                    }
                    case 4 -> {
                        System.out.print("ID do pedido: ");
                        int id = sc.nextInt();
                        dao.deletar(id);
                        System.out.println("Pedido deletado!");
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
