package main;

import dao.ClienteDAO;
import model.Cliente;

import java.sql.SQLException;
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
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    String cpfCnpj, tipo;
                    while (true) {
                        System.out.print("CPF/CNPJ (com ou sem pontuação): ");
                        cpfCnpj = sc.nextLine().replaceAll("\\D", "");

                        if (cpfCnpj.length() == 11) {
                            tipo = "PF";
                        } else if (cpfCnpj.length() == 14) {
                            tipo = "PJ";
                        } else {
                            System.out.println("❌ CPF deve ter 11 dígitos ou CNPJ 14. Tente novamente.");
                            continue;
                        }

                        try {
                            Cliente existente = dao.buscarPorCpfCnpj(cpfCnpj);
                            if (existente != null) {
                                System.out.println("Cliente já cadastrado:");
                                System.out.println(existente);
                                System.out.println("❌ CPF/CNPJ já utilizado.");
                                continue;
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao verificar CPF/CNPJ: " + e.getMessage());
                            continue;
                        }

                        break;
                    }

                    String nome;
                    while (true) {
                        System.out.print("Nome: ");
                        nome = sc.nextLine();
                        if (!nome.isBlank()) break;
                        System.out.println("❌ Campo obrigatório.");
                    }

                    String telefone;
                    while (true) {
                        System.out.print("Telefone (somente números): ");
                        telefone = sc.nextLine().replaceAll("\\D", "");
                        if (telefone.length() == 11) break;
                        System.out.println("❌ Telefone deve ter 11 dígitos.");
                    }

                    String email;
                    while (true) {
                        System.out.print("Email: ");
                        email = sc.nextLine();
                        if (!email.isBlank()) break;
                        System.out.println("❌ Campo obrigatório.");
                    }

                    String endereco;
                    while (true) {
                        System.out.print("Endereço: ");
                        endereco = sc.nextLine();
                        if (!endereco.isBlank()) break;
                        System.out.println("❌ Campo obrigatório.");
                    }

                    Cliente novo = new Cliente(0, nome, cpfCnpj, telefone, email, endereco, tipo, true);
                    try {
                        dao.inserir(novo);
                        System.out.println("✅ Cliente cadastrado com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao inserir cliente: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.println("\n1 - Listar clientes ATIVOS");
                    System.out.println("2 - Listar clientes INATIVOS");
                    System.out.print("Escolha: ");
                    String escolha = sc.nextLine();

                    try {
                        if (escolha.equals("1")) {
                            List<Cliente> ativos = dao.listarAtivos();
                            if (ativos.isEmpty()) {
                                System.out.println("Nenhum cliente ativo cadastrado.");
                            } else {
                                System.out.println("\n===== CLIENTES ATIVOS =====");
                                for (Cliente c : ativos) System.out.println(c);
                            }
                        } else if (escolha.equals("2")) {
                            List<Cliente> inativos = dao.listarInativos();
                            if (inativos.isEmpty()) {
                                System.out.println("Nenhum cliente inativo.");
                            } else {
                                System.out.println("\n===== CLIENTES INATIVOS =====");
                                for (Cliente c : inativos) System.out.println(c);
                            }
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar clientes: " + e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.print("Digite o CPF/CNPJ do cliente a atualizar: ");
                    String cpfCnpj = sc.nextLine().replaceAll("\\D", "");

                    try {
                        Cliente atual = dao.buscarPorCpfCnpj(cpfCnpj);
                        if (atual == null) {
                            System.out.println("Cliente não encontrado.");
                            break;
                        }

                        System.out.println("📝 Dados atuais:");
                        System.out.println(atual);

                        System.out.print("Novo telefone (" + atual.getTelefone() + "): ");
                        String telefone = sc.nextLine().replaceAll("\\D", "");
                        if (!telefone.isEmpty() && telefone.length() == 11) atual.setTelefone(telefone);

                        System.out.print("Novo email (" + atual.getEmail() + "): ");
                        String email = sc.nextLine();
                        if (!email.isEmpty()) atual.setEmail(email);

                        System.out.print("Novo endereço (" + atual.getEndereco() + "): ");
                        String endereco = sc.nextLine();
                        if (!endereco.isEmpty()) atual.setEndereco(endereco);

                        dao.atualizar(atual);
                        System.out.println("✅ Cliente atualizado com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao atualizar cliente: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.print("Digite o CPF/CNPJ do cliente a deletar: ");
                    String cpfCnpj = sc.nextLine().replaceAll("\\D", "");
                    try {
                        dao.deletar(cpfCnpj);
                        System.out.println("✅ Cliente deletado (inativado) com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao deletar cliente: " + e.getMessage());
                    }
                }

                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
