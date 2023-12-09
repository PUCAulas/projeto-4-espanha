package src;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(); 

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Cadastros");
            System.out.println("2. Edições");
            System.out.println("3. Pesquisas");
            System.out.println("4. Empréstimos");
            System.out.println("5. Gerenciar Usuarios");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: \n");

            if (scanner.hasNext()) {
                String opcaoPrincipal = scanner.next();

                switch (opcaoPrincipal) {
                    case "1":
                        menuCadastros(biblioteca, scanner);
                        break;
                    case "2":
                        menuEditar(biblioteca, scanner);
                        break;
                    case "3":
                        menuPesquisas(biblioteca, scanner);
                        break;
                    case "4":
                        menuEmprestimo(biblioteca, scanner);
                        break;
                    case "5":
                        menuUsuario(biblioteca, scanner);
                        break;
                    case "6":
                        System.out.println("Saindo da aplicação.");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4 ou 5).");
                }
            } else {
                System.out.println("Nenhuma entrada disponível. Encerrando o programa.");
                return;
            }
        }
    }

    public static void menuCadastros(Biblioteca biblioteca, Scanner scanner) { 
    while (true) {
        System.out.println("\nMenu Cadastros:");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Cadastrar Revista");
        System.out.println("3. Cadastrar Tese");
        System.out.println("4. Cadastrar CD");
        System.out.println("5. Cadastrar DVD");
        System.out.println("6. Voltar");
        System.out.print("Escolha uma opção: ");

        if (scanner.hasNext()) {
            String opcaoCadastro = scanner.next();

            switch (opcaoCadastro) {
                case "1":
                    biblioteca.adicionarLivro();
                    break;
                case "2":
                    biblioteca.adicionarRevista();
                    break;
                case "3":
                    biblioteca.adicionarTese();
                    break;
                case "4":
                    biblioteca.adicionarCD();
                    break;
                case "5":
                    biblioteca.adicionarDVD();
                    break;
                case "6":
                    return; 
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4, 5, ou 6).");
            }
        } else {
            System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
            return;
        }
    }
}

public static void menuEditar(Biblioteca biblioteca, Scanner scanner) { 
        System.out.println("\nMenu Cadastros:");
        System.out.println("1. Editar Livro");
        System.out.println("2. Editar Revista");
        System.out.println("3. Editar Tese");
        System.out.println("4. Editar CD");
        System.out.println("5. Editar DVD");
        System.out.println("6. Voltar");
        System.out.print("Escolha uma opção: ");

        if (scanner.hasNext()) {
            String opcaoCadastro = scanner.next();

            switch (opcaoCadastro) {
                case "1":
                    biblioteca.editarLivro();
                    break;
                case "2":
                    biblioteca.editarRevista();
                    break;
                case "3":
                    biblioteca.editarTese();
                    break;
                case "4":
                    biblioteca.editarCD();
                    break;
                case "5":
                    biblioteca.editarDVD();
                    break;
                case "6":
                    return; 
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4, 5, ou 6).");
            }
        } else {
            System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
            return;
        }
    }


public static void menuPesquisas(Biblioteca biblioteca, Scanner scanner) { 
while (true) {
    System.out.println("\nMenu Pesquisas:");
    System.out.println("1. Pesquisar por título");
    System.out.println("2. Pesquisar por autor");
    System.out.println("3. Pesquisar por ano");
    System.out.println("4. Pesquisar por tipo");
    System.out.println("5. Voltar");
    System.out.print("Escolha uma opção: ");

    if (scanner.hasNext()) {
        String opcaoPesquisa = scanner.next();

        switch (opcaoPesquisa) {
            case "1":
                biblioteca.pesquisaPorTitulo();
                break;
            case "2":
                biblioteca.pesquisaPorAutor();
                break;
            case "3":
                biblioteca.pesquisaPorAnoPublicacao();
                break;
            case "4":
                menuPesquisasTipo(biblioteca, scanner);
                break;
            case "5":
                return; 
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4, ou 5).");
        }
    } else {
        System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
        return;
    }
}
}

    

public static void menuPesquisasTipo(Biblioteca biblioteca, Scanner scanner) { 
while (true) {
    System.out.println("\nMenu Pesquisas:");
    System.out.println("1. Pesquisar Livros");
    System.out.println("2. Pesquisar Revistas");
    System.out.println("3. Pesquisar Teses");
    System.out.println("4. Pesquisar CDs");
    System.out.println("5. Pesquisar DVDs");
    System.out.println("6. Voltar");
    System.out.print("Escolha uma opção: ");

    if (scanner.hasNext()) {
        String opcaoPesquisa = scanner.next();

        switch (opcaoPesquisa) {
            case "1":
                biblioteca.pesquisaTipoLivro("livros.txt");
                break;
            case "2":
                biblioteca.pesquisaTipoRevista("revistas.txt");
                break;
            case "3":
                biblioteca.pesquisaTipoTese("teses.txt");
                break;
            case "4":
                biblioteca.pesquisaTipoCD("cds.txt");
                break;
            case "5":
                biblioteca.pesquisaTipoDVD("dvds.txt");
                break;
            case "6":
                return;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4, 5, ou 6).");
        }
    } else {
        System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
        return;
    }
}
}

public static void menuEmprestimo(Biblioteca biblioteca, Scanner scanner) { 
        System.out.println("\nMenu Empréstimos:");
        System.out.println("1. Realizar empréstimo");
        System.out.println("2. Realizar Devolução");
        System.out.println("3. Relatório de emprestimos");
        System.out.println("4. Relatório de emprestimos por usuário");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");
        
        if (scanner.hasNext()) {
            String opcaoEmprestimo = scanner.next();

            switch (opcaoEmprestimo) {
                case "1":
                    biblioteca.emprestar();
                    break;
                case "2":
                    biblioteca.devolver();
                    break;
                case "3":
                    biblioteca.gerarRelatorioEmprestimos();
                    break;
                case "4":
                    biblioteca.gerarRelatorioEmprestimosPorUsuario();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4 ou 5).");
            }
        } else {
            System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
            return;
        }
    }

    public static void menuUsuario(Biblioteca biblioteca, Scanner scanner) { 
        System.out.println("\nMenu Cadastros:");
        System.out.println("1. Cadastrar Usuario");
        System.out.println("2. Listar Usuario");
        System.out.println("3. Editar Usuario");
        System.out.println("4. Recomendação Usuario");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");

        if (scanner.hasNext()) {
            String opcaoCadastro = scanner.next();

            switch (opcaoCadastro) {
                case "1":
                    biblioteca.adicionarUsuario();
                    break;
                case "2":
                    biblioteca.listarUsuarios();
                    break;
                case "3":
                    biblioteca.editarUsuario();
                    break;
                case "4":
                    biblioteca.recomendacao();
                    break;
                case "5":
                    return; 
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, 3, 4 ou 5.");
            }
        } else {
            System.out.println("Nenhuma entrada disponível. Voltando para o menu anterior.");
            return;
        }
    }
}
