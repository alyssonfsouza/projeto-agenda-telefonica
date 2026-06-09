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

                        // 1. Verifica se o nome ja existe no banco
                        if (agenda.existeContato(nomeDigitado)) {
                            System.out.println();
                            System.out.println("O contato '" + nomeDigitado + "' ja existe!");
                            System.out.print("Deseja atualizar os dados deste contato? (S/N): ");
                            
                            String resposta = leitor.nextLine();
                            if (resposta.trim().isEmpty()) {
                                resposta = leitor.nextLine();
                            }

                            if (resposta.equalsIgnoreCase("S")) {
                                System.out.println();
                                System.out.println("Retornando ao menu. Escolha a opcao de busca (3) para atualizar usando o ID.");
                                break; // Sai do case 1 e volta para as opcoes do menu
                            } else {
                                System.out.println("Operacao de cadastro cancelada.");
                                break;
                            }
                        } else {
                            // 2. Se o nome NAO existe e nao e vazio, segue o cadastro normal
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

                            // Salva no banco
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

                        // Primeiro, busca e mostra os dados atuais (retorna true se achou)
                        boolean encontrou = agenda.buscarContato(nomeBusca);

                        // Verifica se ele existe para oferecer a atualizacao
                        if (encontrou) {
                            System.out.print("Deseja atualizar algum destes contatos? (S/N): ");
                            String opc = leitor.nextLine();
                            
                            // Trava de seguranca do Scanner
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

                                // Chama o metodo de atualizacao passando o ID exato
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
                        System.out.println();
                        System.out.println("Encerrando sistema...");
                        break;

                    default:
                        System.out.println();
                        System.out.println("Opcao invalida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: Digite apenas numeros onde for solicitado.");
                leitor.nextLine(); // Limpa o buffer caso o erro seja na digitacao
            }
        } while (opcao != 5);

        leitor.close();
    }
}