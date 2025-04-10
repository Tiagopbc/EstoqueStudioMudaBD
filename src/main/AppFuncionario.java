package main;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;

public class AppFuncionario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FuncionarioDAO dao = new FuncionarioDAO();
        int opcao;

        do {
            System.out.println("\n===== MENU FUNCIONÁRIO =====");
            System.out.println("1 - Inserir funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Atualizar funcionário");
            System.out.println("4 - Deletar funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Cargo: ");
                        String cargo = sc.nextLine();
                        dao.inserir(new Funcionario(0, nome, cpf, cargo));
                        System.out.println("Funcionário inserido!");
                    }
                    case 2 -> {
                        List<Funcionario> lista = dao.listar();
                        System.out.println("\nFuncionários cadastrados:");
                        for (Funcionario f : lista) {
                            System.out.println(f.getId() + " - " + f.getNome() + " (" + f.getCargo() + ")");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do funcionário: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Novo cargo: ");
                        String cargo = sc.nextLine();
                        dao.atualizar(new Funcionario(id, nome, cpf, cargo));
                        System.out.println("Funcionário atualizado!");
                    }
                    case 4 -> {
                        System.out.print("ID do funcionário: ");
                        int id = sc.nextInt();
                        dao.deletar(id);
                        System.out.println("Funcionário deletado!");
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
