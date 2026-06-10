import java.util.Scanner;

public class Agenda {
    public static void main(String[] args) {
        AgendaTelefonica agenda = new AgendaTelefonica();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n===== AGENDA TELEFONICA =====");
            System.out.println("1. Adicionar um novo contato.");
            System.out.println("2. Listar todos os contatos.");
            System.out.println("3. Buscar um contato pelo nome.");
            System.out.println("4. Remover um contato existente.");
            System.out.println("5. Sair do programa.");
            System.out.println();
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(leitor.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Adicionar Novo Contato ---");
                        System.out.print("Digite o nome: ");
                        String nomeDigitado = leitor.nextLine();

                        if (agenda.existeContato(nomeDigitado)) {
                            System.out.println("\nO contato '" + nomeDigitado + "' ja existe!");
                            System.out.print("Deseja atualizar os dados deste contato? (S/N): ");
                            
                            String resposta = leitor.nextLine();
                            if (resposta.trim().isEmpty()) {
                                resposta = leitor.nextLine();
                            }

                            if (resposta.equalsIgnoreCase("S")) {
                                System.out.println("\nRetornando ao menu. Escolha a opcao de busca (3) para atualizar usando o ID.");
                                break;
                            } else {
                                System.out.println("Operacao de cadastro cancelada.");
                                break;
                            }
                        } else {
                            if (nomeDigitado.trim().isEmpty()) {
                                System.out.println("Erro: O nome nao pode ser vazio.");
                                break;
                            }

                            Contato novoContato = new Contato();
                            novoContato.setNome(nomeDigitado);

                            System.out.print("Telefone: ");
                            novoContato.setTelefone(leitor.nextLine());

                            System.out.print("Email: ");
                            novoContato.setEmail(leitor.nextLine());

                            agenda.adicionarContato(novoContato);
                        }
                        break;

                    case 2:
                        agenda.listarContatos();
                        break;

                    case 3:
                        System.out.println("\n--- Buscar Contato ---");
                        System.out.print("Digite o nome para buscar: ");
                        String nomeBusca = leitor.nextLine();
                        System.out.println();

                        boolean encontrou = agenda.buscarContato(nomeBusca);

                        if (encontrou) {
                            System.out.print("Deseja atualizar algum destes contatos? (S/N): ");
                            String opc = leitor.nextLine();
                            
                            if (opc.trim().isEmpty()) {
                                opc = leitor.nextLine();
                            }

                            if (opc.equalsIgnoreCase("S")) {
                                System.out.print("Digite o CODIGO (ID) do contato que deseja atualizar: ");
                                int idSelecionado = Integer.parseInt(leitor.nextLine());

                                Contato contatoAtualizado = new Contato();

                                System.out.println("\n--- Digite os NOVOS dados ---");
                                System.out.print("Novo Nome: ");
                                contatoAtualizado.setNome(leitor.nextLine());

                                System.out.print("Novo Telefone: ");
                                contatoAtualizado.setTelefone(leitor.nextLine());

                                System.out.print("Novo Email: ");
                                contatoAtualizado.setEmail(leitor.nextLine());
                                System.out.println();

                                agenda.atualizarContato(idSelecionado, contatoAtualizado);
                            } else {
                                System.out.println("Busca finalizada.");
                            }
                        }
                        break;

                    case 4:
                        System.out.println();
                        System.out.print("Digite o nome EXATO para remover: ");
                        agenda.removerContato(leitor.nextLine());
                        break;

                    case 5:
                        System.out.println("\nEncerrando sistema...");
                        break;

                    default:
                        System.out.println("\nOpcao invalida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: Digite apenas numeros onde for solicitado.");
                leitor.nextLine(); // Limpa o buffer
            }
        } while (opcao != 5);

        leitor.close();
    }
}