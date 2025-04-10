package main;

import dao.ClienteDAO;
import model.Cliente;

import java.util.List;
import java.util.Scanner;

public class AppCliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteDAO dao = new ClienteDAO();
        int opcao;

        do {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("1 - Inserir cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Deletar cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("CPF/CNPJ: ");
                        String cpfCnpj = sc.nextLine();
                        System.out.print("Tipo (PF/PJ): ");
                        String tipo = sc.nextLine();
                        dao.inserir(new Cliente(0, nome, cpfCnpj, tipo));
                        System.out.println("Cliente inserido!");
                    }
                    case 2 -> {
                        List<Cliente> lista = dao.listar();
                        System.out.println("\nClientes cadastrados:");
                        for (Cliente c : lista) {
                            System.out.println(c.getId() + " - " + c.getNome() + " (" + c.getTipo() + ")");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do cliente: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo CPF/CNPJ: ");
                        String cpfCnpj = sc.nextLine();
                        System.out.print("Novo tipo (PF/PJ): ");
                        String tipo = sc.nextLine();
                        dao.atualizar(new Cliente(id, nome, cpfCnpj, tipo));
                        System.out.println("Cliente atualizado!");
                    }
                    case 4 -> {
                        System.out.print("ID do cliente: ");
                        int id = sc.nextInt();
                        dao.deletar(id);
                        System.out.println("Cliente deletado!");
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
