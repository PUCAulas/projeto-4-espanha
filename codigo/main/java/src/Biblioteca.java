package src;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Biblioteca implements Emprestimo {
    private List<Livro> listaLivros;
    private List<Revista> listaRevistas;
    private List<Tese> listaTeses;
    private List<Cd> listaCDs;
    private List<Dvd> listaDVDs;
    private List<Usuario> listaUsuarios;

    public Biblioteca() {
        listaLivros = new ArrayList<>();
        listaRevistas = new ArrayList<>();
        listaTeses = new ArrayList<>();
        listaCDs = new ArrayList<>();
        listaDVDs = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
    }

    public void adicionarUsuario() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            Scanner scanner = new Scanner(System.in);
    
            System.out.println("Adicionar um novo usuário:");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
    
            System.out.println("Escolha o curso:");
            System.out.println("1. Engenharia de Software");
            System.out.println("2. Direito");
            System.out.println("3. Jornalismo");
            System.out.println("4. Medicina");
            int escolhaCurso = scanner.nextInt();
            String curso = obterCurso(escolhaCurso);
    
            System.out.println("Escolha duas categorias de interesse:");
            System.out.println("1. Tecnologia");
            System.out.println("2. Historia");
            System.out.println("3. Informacao");
            System.out.println("4. Biologia");
            int escolhaCategoria1 = scanner.nextInt();
            int escolhaCategoria2 = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            Set<String> categoriasInteresse = obterCategoriasInteresse(escolhaCategoria1, escolhaCategoria2);
    
            int novoId = obterProximoIdDisponivel();
    
            // Criar uma instância de UsuarioAdapter com os dados inseridos
            UsuarioAdaptado usuarioAdapter = new UsuarioAdaptado(nome, cpf, novoId, curso, categoriasInteresse);
    
            // Adicionar o usuário à lista de usuários em memória (se necessário)
            // listaUsuarios.add(usuarioAdapter);
    
            // Escrever os dados do usuário no arquivo "usuarios.txt"
            writer.write(String.format("%s;%s;%d;%s;%s", nome, cpf, novoId, curso, String.join(",", categoriasInteresse)));
            writer.newLine();
    
            System.out.println("Usuário adicionado com sucesso.");
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo usuarios.txt: " + e.getMessage());
        }
    }

    private Set<String> obterCategoriasInteresse(int escolha1, int escolha2) {
        Set<String> categorias = new HashSet<>();
        categorias.add(obterCategoriaPorEscolha(escolha1));
        categorias.add(obterCategoriaPorEscolha(escolha2));
        return categorias;
    }
    
    private String obterCurso(int escolha) {
        switch (escolha) {
            case 1:
                return "Engenharia de Software";
            case 2:
                return "Direito";
            case 3:
                return "Jornalismo";
            case 4:
                return "Medicina";
            default:
                return "Curso Inválido";
        }
    }

    private String obterCategoriaPorEscolha(int escolha) {
        switch (escolha) {
            case 1:
                return "Tecnologia";
            case 2:
                return "Historia";
            case 3:
                return "Informacao";
            case 4:
                return "Biologia";
            default:
                return "Categoria Inválida";
        }
    }
    
    private int obterProximoIdDisponivel() {
        int novoId = 1; // ID inicial
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[2]);
                    if (id >= novoId) {
                        novoId = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo usuarios.txt: " + e.getMessage());
        }
        return novoId;
    }

    public void listarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            System.out.println("Lista de Usuários:");
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) { 
                    int id = Integer.parseInt(dados[2]);
                    String nome = dados[0];
                    String cpf = dados[1];
                    String curso = dados[3];
                    String[] interesses = dados[4].split(","); 
                    
                    String interesse1 = interesses[0]; 
                    String interesse2 = interesses.length > 1 ? interesses[1] : ""; 
                    
                    System.out.println("ID: " + id);
                    System.out.println("Nome: " + nome);
                    System.out.println("CPF: " + cpf);
                    System.out.println("Curso: " + curso);
                    System.out.println("Interesse 1: " + interesse1);
                    System.out.println("Interesse 2: " + interesse2);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo usuarios.txt: " + e.getMessage());
        }
    }
    

    public void editarUsuario() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editar informações de um usuário:");
            System.out.print("Digite o ID do usuário que deseja editar: ");
            int idUsuario = scanner.nextInt();
            scanner.nextLine();
    
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String line;
            StringBuilder fileContents = new StringBuilder();
            boolean encontrado = false;
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[2]);
                    if (id == idUsuario) {
                        encontrado = true;
                        System.out.println("ID: " + idUsuario);
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo CPF: ");
                        String novoCPF = scanner.nextLine();
                        System.out.print("Novo curso: ");
                        String novoCurso = scanner.nextLine();
                        System.out.print("Novo Interesse: ");
                        String novoInteresse1 = scanner.nextLine();
                        System.out.print("Novo Interesse: ");
                        String novoInteresse2 = scanner.nextLine();
                        line = novoNome + ";" + novoCPF + ";" + idUsuario + ";" + novoCurso + ";" + novoInteresse1 + ";" + novoInteresse2; 
                    }
                }
                fileContents.append(line).append("\n");
            }
            reader.close();
            scanner.close();
    
            if (encontrado) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt")); 
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("Usuário editado com sucesso.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao editar o usuário: " + e.getMessage());
        }
    }
    


    private String obterCategoria(int escolha) {
        switch (escolha) {
            case 1:
                return "Tecnologia";
            case 2:
                return "Historia";
            case 3:
                return "Informacao";
            case 4:
                return "Biologia";
            default:
                return "Categoria Inválida";
        }
    }

    // Método para adicionar um livro e escrever no arquivo "livros.txt"
    public void adicionarLivro() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt", true))) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar um novo livro:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Número de exemplares disponíveis: ");
        int exemplaresDisponiveis = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Adicionar prompt para selecionar a categoria
        System.out.println("Escolha a categoria do livro:");
        System.out.println("1. Tecnologia");
        System.out.println("2. Historia");
        System.out.println("3. Informacao");
        System.out.println("4. Biologia");
        int escolhaCategoria = scanner.nextInt();
        String categoria = obterCategoria(escolhaCategoria);

        // Criar uma instância de Livro com os dados inseridos
        Livro livro = new Livro(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);

        // Adicionar o livro à lista de livros em memória
        listaLivros.add(livro);

        // Escrever os dados do livro no arquivo "livros.txt"
        writer.write(String.format("%s;%s;%d;%d;%s", titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria));
        writer.newLine();

        System.out.println("Livro adicionado com sucesso.");
        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo livros.txt: " + e.getMessage());
    }
}

    // Método para adicionar uma revista e escrever no arquivo "revistas.txt"
public void adicionarRevista() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("revistas.txt", true))) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar uma nova revista:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Número de exemplares disponíveis: ");
        int exemplaresDisponiveis = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Adicionar prompt para selecionar a categoria
        System.out.println("Escolha a categoria da revista:");
        System.out.println("1. Tecnologia");
        System.out.println("2. Historia");
        System.out.println("3. Informacao");
        System.out.println("4. Biologia");
        int escolhaCategoria = scanner.nextInt();
        String categoria = obterCategoria(escolhaCategoria);

        // Criar uma instância de Revista com os dados inseridos
        Revista revista = new Revista(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);

        // Adicionar a revista à lista de revistas em memória
        listaRevistas.add(revista);

        // Escrever os dados da revista no arquivo "revistas.txt"
        writer.write(String.format("%s;%s;%d;%d;%s", titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria));
        writer.newLine();

        System.out.println("Revista adicionada com sucesso.");

        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo revistas.txt: " + e.getMessage());
    }
}


    // Método para adicionar uma tese e escrever no arquivo "teses.txt"
public void adicionarTese() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("teses.txt", true))) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar uma nova tese:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Número de exemplares disponíveis: ");
        int exemplaresDisponiveis = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Adicionar prompt para selecionar a categoria
        System.out.println("Escolha a categoria da tese:");
        System.out.println("1. Tecnologia");
        System.out.println("2. Historia");
        System.out.println("3. Informacao");
        System.out.println("4. Biologia");
        int escolhaCategoria = scanner.nextInt();
        String categoria = obterCategoria(escolhaCategoria);

        // Criar uma instância de Tese com os dados inseridos
        Tese tese = new Tese(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);

        // Adicionar a tese à lista de teses em memória
        listaTeses.add(tese);

        // Escrever os dados da tese no arquivo "teses.txt"
        writer.write(String.format("%s;%s;%d;%d;%s", titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria));
        writer.newLine();

        System.out.println("Tese adicionada com sucesso.");
        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo teses.txt: " + e.getMessage());
    }
}


    // Método para adicionar um CD e escrever no arquivo "cds.txt"
public void adicionarCD() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("cds.txt", true))) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar um novo CD:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Número de exemplares disponíveis: ");
        int exemplaresDisponiveis = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Adicionar prompt para selecionar a categoria
        System.out.println("Escolha a categoria do CD:");
        System.out.println("1. Tecnologia");
        System.out.println("2. Historia");
        System.out.println("3. Informacao");
        System.out.println("4. Biologia");
        int escolhaCategoria = scanner.nextInt();
        String categoria = obterCategoria(escolhaCategoria);

        // Criar uma instância de CD com os dados inseridos
        Cd cd = new Cd(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);

        // Adicionar o CD à lista de CDs em memória
        listaCDs.add(cd);

        // Escrever os dados do CD no arquivo "cds.txt"
        writer.write(String.format("%s;%s;%d;%d;%s", titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria));
        writer.newLine();

        System.out.println("CD adicionado com sucesso.");
        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo cds.txt: " + e.getMessage());
    }
}


    // Método para adicionar um DVD e escrever no arquivo "dvds.txt"
public void adicionarDVD() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("dvds.txt", true))) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adicionar um novo DVD:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Número de exemplares disponíveis: ");
        int exemplaresDisponiveis = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Adicionar prompt para selecionar a categoria
        System.out.println("Escolha a categoria do DVD:");
        System.out.println("1. Tecnologia");
        System.out.println("2. Historia");
        System.out.println("3. Informacao");
        System.out.println("4. Biologia");
        int escolhaCategoria = scanner.nextInt();
        String categoria = obterCategoria(escolhaCategoria);

        // Criar uma instância de DVD com os dados inseridos
        Dvd dvd = new Dvd(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);

        // Adicionar o DVD à lista de DVDs em memória
        listaDVDs.add(dvd);

        // Escrever os dados do DVD no arquivo "dvds.txt"
        writer.write(String.format("%s;%s;%d;%d;%s", titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria));
        writer.newLine();

        System.out.println("DVD adicionado com sucesso.");
        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo dvds.txt: " + e.getMessage());
    }
}


    public void pesquisaPorTitulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título da obra que deseja pesquisar: ");
        String tituloPesquisa = scanner.nextLine();

        System.out.println("Resultado da pesquisa por título: " + tituloPesquisa);

        // Pesquisa nos arquivos e exibe as informações
        pesquisaPorTituloArquivo("livros.txt", tituloPesquisa, "Livro");
        pesquisaPorTituloArquivo("revistas.txt", tituloPesquisa, "Revista");
        pesquisaPorTituloArquivo("teses.txt", tituloPesquisa, "Tese");
        pesquisaPorTituloArquivo("cds.txt", tituloPesquisa, "CD");
        pesquisaPorTituloArquivo("dvds.txt", tituloPesquisa, "DVD");
        scanner.close();
    }

    public void pesquisaPorTituloArquivo(String nomeArquivo, String tituloPesquisa, String tipo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(tituloPesquisa)) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = parts[4];

                    System.out.println("Tipo: " + tipo);
                    System.out.println("Título: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Ano de Publicação: " + anoPublicacao);
                    System.out.println("Exemplares Disponíveis: " + exemplaresDisponiveis);
                    System.out.println("Categoria: " + categoria);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public void pesquisaPorAutor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do autor que deseja pesquisar: ");
        String autorPesquisa = scanner.nextLine();

        System.out.println("Resultado da pesquisa por autor: " + autorPesquisa);

        // Pesquisa nos arquivos e exibe as informações
        pesquisaPorAutorArquivo("livros.txt", autorPesquisa, "Livro");
        pesquisaPorAutorArquivo("revistas.txt", autorPesquisa, "Revista");
        pesquisaPorAutorArquivo("teses.txt", autorPesquisa, "Tese");
        pesquisaPorAutorArquivo("cds.txt", autorPesquisa, "CD");
        pesquisaPorAutorArquivo("dvds.txt", autorPesquisa, "DVD");

        scanner.close();
    }

    public void pesquisaPorAutorArquivo(String nomeArquivo, String autorPesquisa, String tipo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[1].equalsIgnoreCase(autorPesquisa)) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = parts[4];


                    System.out.println("Tipo: " + tipo);
                    System.out.println("Título: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Ano de Publicação: " + anoPublicacao);
                    System.out.println("Exemplares Disponíveis: " + exemplaresDisponiveis);
                    System.out.println("Categoria: " + categoria);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public void pesquisaPorAnoPublicacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ano de publicação que deseja pesquisar: ");
        int anoPesquisa = scanner.nextInt();

        System.out.println("Resultado da pesquisa por ano de publicação: " + anoPesquisa);

        // Pesquisa nos arquivos e exibe as informações
        pesquisaPorAnoPublicacaoArquivo("livros.txt", anoPesquisa, "Livro");
        pesquisaPorAnoPublicacaoArquivo("revistas.txt", anoPesquisa, "Revista");
        pesquisaPorAnoPublicacaoArquivo("teses.txt", anoPesquisa, "Tese");
        pesquisaPorAnoPublicacaoArquivo("cds.txt", anoPesquisa, "CD");
        pesquisaPorAnoPublicacaoArquivo("dvds.txt", anoPesquisa, "DVD");

        scanner.close();
    }

    public void pesquisaPorAnoPublicacaoArquivo(String nomeArquivo, int anoPesquisa, String tipo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    if (anoPublicacao == anoPesquisa) {
                        String titulo = parts[0];
                        String autor = parts[1];
                        int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                        String categoria = parts[4];

                        System.out.println("Tipo: " + tipo);
                        System.out.println("Título: " + titulo);
                        System.out.println("Autor: " + autor);
                        System.out.println("Ano de Publicação: " + anoPublicacao);
                        System.out.println("Exemplares Disponíveis: " + exemplaresDisponiveis);
                        System.out.println("Categoria: " + categoria);
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    // Método para pesquisar livros por título em ordem alfabética
    public void pesquisaTipoLivro(String filePath) {
        List<Livro> livros = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = (parts[4]);

                    Livro livro = new Livro(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo livros.txt: " + e.getMessage());
        }

        // Ordenar a lista de livros por título em ordem alfabética
        Collections.sort(livros, (livro1, livro2) -> livro1.getTitulo().compareTo(livro2.getTitulo()));

        // Exibir a lista ordenada
        System.out.println("Livros em ordem alfabética por título:");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Exemplares Disponíveis: " + livro.getExemplaresDisponiveis());
            System.out.println();
        }
    }

    // Método para pesquisar revistas por título em ordem alfabética
    public void pesquisaTipoRevista(String filePath) {
        List<Revista> revistas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = (parts[4]);

                    Revista revista = new Revista(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
                    revistas.add(revista);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo revistas.txt: " + e.getMessage());
        }

        // Ordenar a lista de revistas por título em ordem alfabética
        Collections.sort(revistas, (revista1, revista2) -> revista1.getTitulo().compareTo(revista2.getTitulo()));

        // Exibir a lista ordenada
        System.out.println("Revistas em ordem alfabética por título:");
        for (Revista revista : revistas) {
            System.out.println("Título: " + revista.getTitulo());
            System.out.println("Autor: " + revista.getAutor());
            System.out.println("Ano de Publicação: " + revista.getAnoPublicacao());
            System.out.println("Exemplares Disponíveis: " + revista.getExemplaresDisponiveis());
            System.out.println();
        }
    }

    // Método para pesquisar teses por título em ordem alfabética
    public void pesquisaTipoTese(String filePath) {
        List<Tese> teses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = (parts[4]);

                    Tese tese = new Tese(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
                    teses.add(tese);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo teses.txt: " + e.getMessage());
        }

        // Ordenar a lista de teses por título em ordem alfabética
        Collections.sort(teses, (tese1, tese2) -> tese1.getTitulo().compareTo(tese2.getTitulo()));

        // Exibir a lista ordenada
        System.out.println("Teses em ordem alfabética por título:");
        for (Tese tese : teses) {
            System.out.println("Título: " + tese.getTitulo());
            System.out.println("Autor: " + tese.getAutor());
            System.out.println("Ano de Publicação: " + tese.getAnoPublicacao());
            System.out.println("Exemplares Disponíveis: " + tese.getExemplaresDisponiveis());
            System.out.println();
        }
    }

    // Método para pesquisar CDs por título em ordem alfabética
    public void pesquisaTipoCD(String filePath) {
        List<Cd> cds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = (parts[4]);

                    Cd cd = new Cd(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
                    cds.add(cd);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo cds.txt: " + e.getMessage());
        }

        // Ordenar a lista de CDs por título em ordem alfabética
        Collections.sort(cds, (cd1, cd2) -> cd1.getTitulo().compareTo(cd2.getTitulo()));

        // Exibir a lista ordenada
        System.out.println("CDs em ordem alfabética por título:");
        for (Cd cd : cds) {
            System.out.println("Título: " + cd.getTitulo());
            System.out.println("Autor: " + cd.getAutor());
            System.out.println("Ano de Publicação: " + cd.getAnoPublicacao());
            System.out.println("Exemplares Disponíveis: " + cd.getExemplaresDisponiveis());
            System.out.println();
        }
    }

    // Método para pesquisar DVDs por título em ordem alfabética
    public void pesquisaTipoDVD(String filePath) {
        List<Dvd> dvds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String titulo = parts[0];
                    String autor = parts[1];
                    int anoPublicacao = Integer.parseInt(parts[2]);
                    int exemplaresDisponiveis = Integer.parseInt(parts[3]);
                    String categoria = (parts[4]);

                    Dvd dvd = new Dvd(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
                    dvds.add(dvd);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo dvds.txt: " + e.getMessage());
        }

        // Ordenar a lista de DVDs por título em ordem alfabética
        Collections.sort(dvds, (dvd1, dvd2) -> dvd1.getTitulo().compareTo(dvd2.getTitulo()));

        // Exibir a lista ordenada
        System.out.println("DVDs em ordem alfabética por título:");
        for (Dvd dvd : dvds) {
            System.out.println("Título: " + dvd.getTitulo());
            System.out.println("Autor: " + dvd.getAutor());
            System.out.println("Ano de Publicação: " + dvd.getAnoPublicacao());
            System.out.println("Exemplares Disponíveis: " + dvd.getExemplaresDisponiveis());
            System.out.println();
        }
    }

    public void editarLivro() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editar informações de um livro:");
            System.out.print("Digite o título do livro que deseja editar: ");
            String titulo = scanner.nextLine();
    
            // Abre o arquivo "livros.txt" para leitura
            BufferedReader reader = new BufferedReader(new FileReader("livros.txt"));
            String line;
            StringBuilder fileContents = new StringBuilder();
    
            // Procura o livro no arquivo e permite a edição
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(titulo)) {
                    encontrado = true;
                    System.out.println("Título: " + parts[0]);
                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo autor: ");
                    String novoAutor = scanner.nextLine();
                    System.out.print("Novo ano de publicação: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Novo número de exemplares disponíveis: ");
                    int novoExemplares = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
    
                    // Adicionar prompt para selecionar a nova categoria
                    System.out.println("Escolha a nova categoria do livro:");
                    System.out.println("1. Tecnologia");
                    System.out.println("2. Historia");
                    System.out.println("3. Informacao");
                    System.out.println("4. Biologia");
                    int escolhaCategoria = scanner.nextInt();
                    String novaCategoria = obterCategoria(escolhaCategoria);
    
                    // Edita as informações do livro
                    line = novoTitulo + ";" + novoAutor + ";" + novoAno + ";" + novoExemplares + ";" + novaCategoria;
                }
                fileContents.append(line).append("\n");
            }
            reader.close();
    
            if (encontrado) {
                // Abre o arquivo "livros.txt" para escrita e atualiza as informações
                BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt"));
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("Livro editado com sucesso.");
            } else {
                System.out.println("Livro não encontrado.");
            }
    
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erro ao editar o livro: " + e.getMessage());
        }
    }
    

    public void editarRevista() {
        try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editar informações de uma revista:");
        System.out.print("Digite o título da revista que deseja editar: ");
        String titulo = scanner.nextLine();
                // Abre o arquivo "revistas.txt" para leitura
                BufferedReader reader = new BufferedReader(new FileReader("revistas.txt"));
                String line;
                StringBuilder fileContents = new StringBuilder();
        
                // Procura a revista no arquivo e permite a edição
                boolean encontrado = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 5 && parts[0].equalsIgnoreCase(titulo)) {
                        encontrado = true;
                        System.out.println("Título: " + parts[0]);
                        System.out.print("Novo título: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.print("Novo autor: ");
                        String novoAutor = scanner.nextLine();
                        System.out.print("Novo ano de publicação: ");
                        int novoAno = scanner.nextInt();
                        System.out.print("Novo número de exemplares disponíveis: ");
                        int novoExemplares = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha
        
                        // Adicionar prompt para selecionar a nova categoria
                        System.out.println("Escolha a nova categoria da revista:");
                        System.out.println("1. Tecnologia");
                        System.out.println("2. Historia");
                        System.out.println("3. Informacao");
                        System.out.println("4. Biologia");
                        int escolhaCategoria = scanner.nextInt();
                        String novaCategoria = obterCategoria(escolhaCategoria);
        
                        // Edita as informações da revista
                        line = novoTitulo + ";" + novoAutor + ";" + novoAno + ";" + novoExemplares + ";" + novaCategoria;
                    }
                    fileContents.append(line).append("\n");
                }
                reader.close();
        
                if (encontrado) {
                    // Abre o arquivo "revistas.txt" para escrita e atualiza as informações
                    BufferedWriter writer = new BufferedWriter(new FileWriter("revistas.txt"));
                    writer.write(fileContents.toString());
                    writer.close();
                    System.out.println("Revista editada com sucesso.");
                } else {
                    System.out.println("Revista não encontrada.");
                }
        
                scanner.close();
            } catch (IOException e) {
                System.err.println("Erro ao editar a revista: " + e.getMessage());
            }
        }
        
    
    

    public void editarTese() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editar informações de uma tese:");
            System.out.print("Digite o título da tese que deseja editar: ");
            String titulo = scanner.nextLine();
    
            // Abre o arquivo "teses.txt" para leitura
            BufferedReader reader = new BufferedReader(new FileReader("teses.txt"));
            String line;
            StringBuilder fileContents = new StringBuilder();
    
            // Procura a tese no arquivo e permite a edição
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(titulo)) {
                    encontrado = true;
                    System.out.println("Título: " + parts[0]);
                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo autor: ");
                    String novoAutor = scanner.nextLine();
                    System.out.print("Novo ano de publicação: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Novo número de exemplares disponíveis: ");
                    int novoExemplares = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
    
                    // Adicionar prompt para selecionar a nova categoria
                    System.out.println("Escolha a nova categoria da tese:");
                    System.out.println("1. Tecnologia");
                    System.out.println("2. Historia");
                    System.out.println("3. Informacaoo");
                    System.out.println("4. Biologia");
                    int escolhaCategoria = scanner.nextInt();
                    String novaCategoria = obterCategoria(escolhaCategoria);
    
                    // Edita as informações da tese
                    line = novoTitulo + ";" + novoAutor + ";" + novoAno + ";" + novoExemplares + ";" + novaCategoria;
                }
                fileContents.append(line).append("\n");
            }
            reader.close();
    
            if (encontrado) {
                // Abre o arquivo "teses.txt" para escrita e atualiza as informações
                BufferedWriter writer = new BufferedWriter(new FileWriter("teses.txt"));
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("Tese editada com sucesso.");
            } else {
                System.out.println("Tese não encontrada.");
            }
    
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erro ao editar a tese: " + e.getMessage());
        }
    }
    

    public void editarCD() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editar informações de um CD:");
            System.out.print("Digite o título do CD que deseja editar: ");
            String titulo = scanner.nextLine();
    
            // Abre o arquivo "cds.txt" para leitura
            BufferedReader reader = new BufferedReader(new FileReader("cds.txt"));
            String line;
            StringBuilder fileContents = new StringBuilder();
    
            // Procura o CD no arquivo e permite a edição
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(titulo)) {
                    encontrado = true;
                    System.out.println("Título: " + parts[0]);
                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo autor: ");
                    String novoAutor = scanner.nextLine();
                    System.out.print("Novo ano de publicação: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Novo número de exemplares disponíveis: ");
                    int novoExemplares = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
    
                    // Adicionar prompt para selecionar a nova categoria
                    System.out.println("Escolha a nova categoria do CD:");
                    System.out.println("1. Tecnologia");
                    System.out.println("2. Historia");
                    System.out.println("3. Informaaoo");
                    System.out.println("4. Biologia");
                    int escolhaCategoria = scanner.nextInt();
                    String novaCategoria = obterCategoria(escolhaCategoria);
    
                    // Edita as informações do CD
                    line = novoTitulo + ";" + novoAutor + ";" + novoAno + ";" + novoExemplares + ";" + novaCategoria;
                }
                fileContents.append(line).append("\n");
            }
            reader.close();
    
            if (encontrado) {
                // Abre o arquivo "cds.txt" para escrita e atualiza as informações
                BufferedWriter writer = new BufferedWriter(new FileWriter("cds.txt"));
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("CD editado com sucesso.");
            } else {
                System.out.println("CD não encontrado.");
            }
    
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erro ao editar o CD: " + e.getMessage());
        }
    }
    

    public void editarDVD() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Editar informações de um DVD:");
            System.out.print("Digite o título do DVD que deseja editar: ");
            String titulo = scanner.nextLine();
    
            // Abre o arquivo "dvds.txt" para leitura
            BufferedReader reader = new BufferedReader(new FileReader("dvds.txt"));
            String line;
            StringBuilder fileContents = new StringBuilder();
    
            // Procura o DVD no arquivo e permite a edição
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(titulo)) {
                    encontrado = true;
                    System.out.println("Título: " + parts[0]);
                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo autor: ");
                    String novoAutor = scanner.nextLine();
                    System.out.print("Novo ano de publicação: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Novo número de exemplares disponíveis: ");
                    int novoExemplares = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
    
                    // Adicionar prompt para selecionar a nova categoria
                    System.out.println("Escolha a nova categoria do DVD:");
                    System.out.println("1. Tecnologia");
                    System.out.println("2. Historia");
                    System.out.println("3. Informacao");
                    System.out.println("4. Biologia");
                    int escolhaCategoria = scanner.nextInt();
                    String novaCategoria = obterCategoria(escolhaCategoria);
    
                    // Edita as informações do DVD
                    line = novoTitulo + ";" + novoAutor + ";" + novoAno + ";" + novoExemplares + ";" + novaCategoria;
                }
                fileContents.append(line).append("\n");
            }
            reader.close();
    
            if (encontrado) {
                // Abre o arquivo "dvds.txt" para escrita e atualiza as informações
                BufferedWriter writer = new BufferedWriter(new FileWriter("dvds.txt"));
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("DVD editado com sucesso.");
            } else {
                System.out.println("DVD não encontrado.");
            }
    
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erro ao editar o DVD: " + e.getMessage());
        }
    }
    

    public void emprestar() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar ID, título e tipo do item ao usuário
        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();
        System.out.print("Digite o título do item que deseja emprestar: ");
        String tituloItem = scanner.nextLine();
        System.out.print("Digite o tipo do item (Livro, CD ou DVD): ");
        String tipoItem = scanner.nextLine();

        scanner.close();

        // Verificar se o usuário possui empréstimos em atraso
        if (temEmprestimoEmAtraso(idUsuario)) {
            System.out.println(
                    "\n ALERTA: Você possui empréstimos em atraso e não pode pegar novos itens no momento. \n");
            return;
        }

        // Verificar se o usuário atingiu o limite de 3 empréstimos simultâneos
        if (contarEmprestimosAtuais(idUsuario) >= 3) {
            System.out.println("\n ALERTA: Você já atingiu o limite de 3 empréstimos simultâneos. \n ");
            return;
        }

        // Verificar a disponibilidade do item
        if (!verificarDisponibilidade(tituloItem)) {
            System.out.println("\n ALERTA: O item não está disponível para empréstimo. \n ");
            return;
        }

        if (!usuarioRegistradoNoArquivo(idUsuario)) {
            System.out.println("\n ALERTA: Usuário não registrado na biblioteca. \n");
            return;
        }

        // Verificar se o usuario ja pegou esse livro emprestado
        if (itemJaEmprestadoAoUsuario(idUsuario, tituloItem)) {
            System.out.println("\n ALERTA: O item já foi emprestado para esse usuário. \n");
            return;
        }

        // Registrar o empréstimo com base no tipo do item
        if (tipoItem.equalsIgnoreCase("Livro")) {
            registrarEmprestimo(idUsuario, tituloItem);
            diminuirQuantidadeLivros(tituloItem);
        } else if (tipoItem.equalsIgnoreCase("CD")) {
            registrarEmprestimo(idUsuario, tituloItem);
            diminuirQuantidadeCD(tituloItem);
        } else if (tipoItem.equalsIgnoreCase("DVD")) {
            registrarEmprestimo(idUsuario, tituloItem);
            diminuirQuantidadeDVD(tituloItem);
        } else {
            System.out.println("\n ALERTA: Tipo de item inválido. Use 'Livro', 'CD' ou 'DVD'.\n ");
        }

    }

    public boolean usuarioRegistradoNoArquivo(String idUsuario) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    String id = partes[2];
                    if (id.equals(idUsuario)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar registro do usuário: " + e.getMessage());
        }
        return false;
    }
    

    public boolean itemJaEmprestadoAoUsuario(String idUsuario, String tituloItem) {
        System.out.println("Verificando se o item " + tituloItem + " já foi emprestado.");
    
        try (BufferedReader reader = new BufferedReader(new FileReader("emprestimos.txt"))) {
            System.out.println("Analisando arquivo de empréstimos");
            String line;
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
    
                if (parts.length == 4) {
                    String usuario = parts[0];
                    String titulo = parts[2];
                    String status = parts[3];
    
                    if (usuario.equals(idUsuario) && titulo.equals(tituloItem)) {
                        if (status.equals("Pendente")) {
                            //Usuario já pegou esse item emprestado retorna true
                            return true;
                        }
                    }
                }
            }
            return false; // Se não encontrar o empréstimo pendente para o usuário e item específicos
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
    }
    

    private void registrarEmprestimo(String idUsuario, String tituloItem) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataEmprestimo = dateFormat.format(new Date());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("emprestimos.txt", true))) {
            writer.write(String.format("%s;%s;%s;Pendente", idUsuario, dataEmprestimo, tituloItem));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao registrar o empréstimo: " + e.getMessage());
        }
    }

    public boolean verificarDisponibilidade(String tituloItem) {
        System.out.println("Verificando disponibilidade do item: " + tituloItem);
    
        boolean disponibilidadeEncontrada = false;
    
        disponibilidadeEncontrada = verificarDisponibilidadeArquivo("livros.txt", tituloItem)
                || disponibilidadeEncontrada;
        disponibilidadeEncontrada = verificarDisponibilidadeArquivo("cds.txt", tituloItem)
                || disponibilidadeEncontrada;
        disponibilidadeEncontrada = verificarDisponibilidadeArquivo("dvds.txt", tituloItem)
                || disponibilidadeEncontrada;
    
        return disponibilidadeEncontrada;
    }
    
    private boolean verificarDisponibilidadeArquivo(String nomeArquivo, String tituloItem) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            int contagemExemplares = 0;
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5 && parts[0].trim().equalsIgnoreCase(tituloItem)) {
                    contagemExemplares += Integer.parseInt(parts[3].trim());
                }
            }
    
            if (contagemExemplares > 1) {
                System.out.println("Há mais de um exemplar disponível de '" + tituloItem + "'.");
                return true;
            } else if (contagemExemplares == 1) {
                System.out.println("\n ALERTA: Apenas um exemplar disponível de '" + tituloItem + "'. \n");
                return false;
            } else {
                System.out.println("'" + tituloItem + "' não encontrado no arquivo " + nomeArquivo + " ou não está disponível.");
                return false;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
            return false;
        }
    }
    
    
    

    public boolean temEmprestimoEmAtraso(String idUsuario) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader("emprestimos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[0].equals(idUsuario)) {
                    String dataEmprestimoStr = parts[1];
                    LocalDate dataEmprestimo = LocalDate.parse(dataEmprestimoStr, formatter);

                    String status = parts[3];
                    if (status.equals("Pendente")) {
                        // Calcule a diferença em dias entre a data atual e a data de empréstimo.
                        long diferencaDias = dataEmprestimo.until(dataAtual).getDays();

                        if (diferencaDias > 10) {
                            // Empréstimo em atraso, retorne true.
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo emprestimos.txt: " + e.getMessage());
        }

        // Se nenhum empréstimo em atraso for encontrado, retorne false.
        return false;
    }

    public int contarEmprestimosAtuais(String idUsuario) {
        int emprestimosAtuais = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("emprestimos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[0].equals(idUsuario) && !parts[3].equalsIgnoreCase("Devolvido")) {
                    // Verifique se o empréstimo é associado ao usuário e se não foi devolvido.
                    emprestimosAtuais++;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo emprestimos.txt: " + e.getMessage());
        }

        return emprestimosAtuais;
    }

    public void diminuirQuantidadeLivros(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "livros.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o livro pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String livroTitulo = partes[0];

                if (livroTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade--; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste livro.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de livros: " + e.getMessage());
        }
    }

    public void diminuirQuantidadeCD(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "cds.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o CD pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String cdTitulo = partes[0];

                if (cdTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade--; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste CD.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de CDs: " + e.getMessage());
        }
    }

    public void diminuirQuantidadeDVD(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "dvds.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o DVD pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String dvdTitulo = partes[0];

                if (dvdTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade--; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste DVD.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de DVDs: " + e.getMessage());
        }
    }

    public void devolver() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o ID do usuário: ");
            String idUsuario = scanner.nextLine();

            System.out.print("Digite o título do item a ser devolvido: ");
            String tituloItem = scanner.nextLine();

            System.out.print("Digite o tipo do item (Livro, CD ou DVD): ");
            String tipoItem = scanner.nextLine();

            boolean emprestimoEncontrado = false;

            try {
                File emprestimosFile = new File("emprestimos.txt");
                File tempFile = new File("temp_emprestimos.txt");

                BufferedReader reader = new BufferedReader(new FileReader(emprestimosFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 4 && parts[0].equals(idUsuario) && parts[2].equalsIgnoreCase(tituloItem)) {
                        if (parts[3].equalsIgnoreCase("Pendente")) {
                            parts[3] = "Devolvido";
                            emprestimoEncontrado = true;
                            System.out.println("Item devolvido com sucesso!");
                        } else {
                            System.out.println("Este item já foi devolvido ou não está pendente de devolução.");
                        }
                    }
                    writer.write(String.join(";", parts));
                    writer.newLine();
                }

                // Registrar o empréstimo com base no tipo do item
                if (tipoItem.equalsIgnoreCase("Livro")) {
                    aumentarQuantidadeLivros(tituloItem);
                } else if (tipoItem.equalsIgnoreCase("CD")) {
                    aumentarQuantidadeCD(tituloItem);
                } else if (tipoItem.equalsIgnoreCase("DVD")) {
                    aumentarQuantidadeDVD(tituloItem);
                } else {
                    System.out.println("Tipo de item inválido. Use 'Livro', 'CD' ou 'DVD'.");
                }

                reader.close();
                writer.close();

                // Exclui o arquivo original.
                if (emprestimosFile.delete()) {
                    // Renomeia o arquivo temporário para o nome original.
                    if (!tempFile.renameTo(emprestimosFile)) {
                        System.err.println("Erro ao renomear o arquivo temporário.");
                    }
                } else {
                    System.err.println("Erro ao excluir o arquivo original.");
                }

                if (!emprestimoEncontrado) {
                    System.out.println("Nenhum empréstimo pendente encontrado para este usuário e item.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao processar o arquivo de empréstimos: " + e.getMessage());
            }
            scanner.close();
        }
    }

    public void aumentarQuantidadeLivros(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "livros.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o livro pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String livroTitulo = partes[0];

                if (livroTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade++; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste livro.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de livros: " + e.getMessage());
        }
    }

    public void aumentarQuantidadeCD(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "cds.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o CD pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String cdTitulo = partes[0];

                if (cdTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade++; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste CD.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de CDs: " + e.getMessage());
        }
    }

    public void aumentarQuantidadeDVD(String tituloItem) {
        try {
            // Defina o nome do arquivo
            String arquivo = "dvds.txt";

            // Crie uma lista para armazenar as linhas do arquivo
            List<String> linhas = new ArrayList<>();

            // Abra o arquivo para leitura
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;

            // Leia as linhas do arquivo e armazene na lista
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
            br.close();

            // Procurar o DVD pelo título e decrementar a quantidade
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");
                String dvdTitulo = partes[0];

                if (dvdTitulo.equals(tituloItem)) {
                    int quantidade = Integer.parseInt(partes[3]);
                    if (quantidade > 0) {
                        quantidade++; // Decrementa a quantidade
                        partes[3] = Integer.toString(quantidade);
                        linhas.set(i, String.join(";", partes));
                    } else {
                        System.out.println("Não há mais cópias disponíveis deste DVD.");
                        return;
                    }
                }
            }

            // Abra o arquivo para escrita
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            // Escreva as linhas atualizadas de volta no arquivo
            for (String linhaAtualizada : linhas) {
                bw.write(linhaAtualizada);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.println("Erro ao diminuir a quantidade de DVDs: " + e.getMessage());
        }
    }

    public void gerarRelatorioEmprestimos() {
        try (BufferedReader readerEmprestimos = new BufferedReader(new FileReader("emprestimos.txt"));
                BufferedReader readerLivros = new BufferedReader(new FileReader("livros.txt"));
                BufferedReader readerCDs = new BufferedReader(new FileReader("cds.txt"));
                BufferedReader readerDVDs = new BufferedReader(new FileReader("dvds.txt"))) {

            Map<String, Integer> emprestimosPorTitulo = new HashMap<>();
            String linha;

            while ((linha = readerEmprestimos.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    String titulo = partes[2];
                    emprestimosPorTitulo.put(titulo, emprestimosPorTitulo.getOrDefault(titulo, 0) + 1);
                }
            }

            Map<String, Integer> anoPublicacaoPorTitulo = new HashMap();
            carregarAnoPublicacao(readerLivros, anoPublicacaoPorTitulo);
            carregarAnoPublicacao(readerCDs, anoPublicacaoPorTitulo);
            carregarAnoPublicacao(readerDVDs, anoPublicacaoPorTitulo);

            // Classificar o mapa por ano de publicação
            Map<String, Integer> relatorioOrdenado = emprestimosPorTitulo.entrySet().stream()
                    .sorted((e1, e2) -> {
                        int ano1 = anoPublicacaoPorTitulo.getOrDefault(e1.getKey(), 0);
                        int ano2 = anoPublicacaoPorTitulo.getOrDefault(e2.getKey(), 0);
                        return Integer.compare(ano1, ano2);
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                            LinkedHashMap::new));

            // Iterar sobre o mapa e imprimir o relatório
            for (Map.Entry<String, Integer> entry : relatorioOrdenado.entrySet()) {
                String titulo = entry.getKey();
                int quantidadeEmprestimos = entry.getValue();
                System.out.println(titulo + ": Emprestado " + quantidadeEmprestimos + " vezes");
            }
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório de empréstimos: " + e.getMessage());
        }
    }

    private void carregarAnoPublicacao(BufferedReader reader, Map<String, Integer> mapaAnoPublicacao)
            throws IOException {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes.length == 4) {
                String titulo = partes[0];
                int anoPublicacao = Integer.parseInt(partes[2]);
                mapaAnoPublicacao.put(titulo, anoPublicacao);
            }
        }
    }

    public void gerarRelatorioEmprestimosPorUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do usuário que deseja consultar: ");
        String idUsuarioConsulta = scanner.nextLine();

        try (BufferedReader readerEmprestimos = new BufferedReader(new FileReader("emprestimos.txt"))) {
            Map<String, List<String>> emprestimosPorUsuario = new HashMap<>();
            String linha;

            while ((linha = readerEmprestimos.readLine()) != null) {
                String[] partes = linha.split(";");
                String idUsuario = partes[0];
                String tituloItem = partes[2];
                if (idUsuario.equals(idUsuarioConsulta)) {
                    emprestimosPorUsuario.computeIfAbsent(idUsuario, k -> new ArrayList<>()).add(tituloItem);
                }
            }

            if (emprestimosPorUsuario.isEmpty()) {
                System.out.println("Nenhum empréstimo encontrado para o usuário com ID " + idUsuarioConsulta);
            } else {
                // Classificar a lista de itens em ordem alfabética para o usuário consultado
                emprestimosPorUsuario.forEach((idUsuario, emprestimos) -> {
                    Collections.sort(emprestimos);
                });

                // Imprimir o relatório para o usuário consultado
                System.out.println("Empréstimos para o usuário ID " + idUsuarioConsulta + ":");
                for (String tituloItem : emprestimosPorUsuario.get(idUsuarioConsulta)) {
                    System.out.println("- " + tituloItem);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório de empréstimos por usuário: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }



    public List<String> recomendacaoInteresse(String idUsuario) {
        List<String> recomendacoes = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5 && partes[2].equalsIgnoreCase(idUsuario)) {
                    String[] interesses = partes[4].split(",");
                    for (String interesse : interesses) {
                        String recomendacao = buscarObraPorCategoria("livros.txt", interesse);
                        if (recomendacao != null) {
                            recomendacoes.add(recomendacao);
                        }
    
                        recomendacao = buscarObraPorCategoria("revistas.txt", interesse);
                        if (recomendacao != null) {
                            recomendacoes.add(recomendacao);
                        }
    
                        recomendacao = buscarObraPorCategoria("teses.txt", interesse);
                        if (recomendacao != null) {
                            recomendacoes.add(recomendacao);
                        }
    
                        recomendacao = buscarObraPorCategoria("cds.txt", interesse);
                        if (recomendacao != null) {
                            recomendacoes.add(recomendacao);
                        }
    
                        recomendacao = buscarObraPorCategoria("dvds.txt", interesse);
                        if (recomendacao != null) {
                            recomendacoes.add(recomendacao);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao recomendar com base em interesses: " + e.getMessage());
        }
    
        if (!recomendacoes.isEmpty()) {
            return recomendacoes;
        } else {
            recomendacoes.add("Nenhuma recomendação encontrada com base em interesses.");
            return recomendacoes;
        }
    }
    
    
    private String buscarObraPorCategoria(String nomeArquivo, String categoria) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5 && partes[4].equalsIgnoreCase(categoria)) {
                    return partes[0]; // Retorna o título da obra
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar obra por categoria: " + e.getMessage());
        }
        return null; // Retorna null se nenhuma obra for encontrada com a categoria específica
    }

    public String recomendacaoPersonalizada(String idUsuario) {
        try (BufferedReader emprestimosReader = new BufferedReader(new FileReader("emprestimos.txt"))) {
            // Mapeia as categorias e a quantidade de vezes que o usuário pegou emprestado uma obra de cada categoria
            Map<String, Integer> categoriasContagem = new HashMap<>();

            String emprestimo;
            while ((emprestimo = emprestimosReader.readLine()) != null) {
                String[] partesEmprestimo = emprestimo.split(";");
                if (partesEmprestimo.length == 4 && partesEmprestimo[0].equals(idUsuario) && partesEmprestimo[3].equals("Pendente")) {
                    String titulo = partesEmprestimo[2];
                    String categoria = buscarCategoriaPorObra("livros.txt", titulo);
                    if (categoria != null) {
                        categoriasContagem.put(categoria, categoriasContagem.getOrDefault(categoria, 0) + 1);
                    }

                    categoria = buscarCategoriaPorObra("revistas.txt", titulo);
                    if (categoria != null) {
                    categoriasContagem.put(categoria, categoriasContagem.getOrDefault(categoria, 0) + 1);
                    }

                    categoria = buscarCategoriaPorObra("teses.txt", titulo);
                    if (categoria != null) {
                    categoriasContagem.put(categoria, categoriasContagem.getOrDefault(categoria, 0) + 1);
                    }

                    categoria = buscarCategoriaPorObra("cds.txt", titulo);
                    if (categoria != null) {
                    categoriasContagem.put(categoria, categoriasContagem.getOrDefault(categoria, 0) + 1);
                    }

                    categoria = buscarCategoriaPorObra("dvds.txt", titulo);
                    if (categoria != null) {
                    categoriasContagem.put(categoria, categoriasContagem.getOrDefault(categoria, 0) + 1);
                    }

                }
            }

            // Encontrar a categoria mais frequente
            String categoriaMaisFrequente = encontrarCategoriaMaisFrequente(categoriasContagem);

            // Buscar a primeira obra na categoria mais frequente
            String recomendacao = buscarObraPorCategoria("livros.txt", categoriaMaisFrequente);
            if (recomendacao != null) {
                return recomendacao;
            }

            recomendacao = buscarObraPorCategoria("revistas.txt", categoriaMaisFrequente);
            if (recomendacao != null) {
            return recomendacao;
            }

            recomendacao = buscarObraPorCategoria("teses.txt", categoriaMaisFrequente);
            if (recomendacao != null) {
            return recomendacao;
            }

            recomendacao = buscarObraPorCategoria("cds.txt", categoriaMaisFrequente);
            if (recomendacao != null) {
            return recomendacao;
            }

            recomendacao = buscarObraPorCategoria("dvds.txt", categoriaMaisFrequente);
            if (recomendacao != null) {
            return recomendacao;
            }


        } catch (IOException e) {
            System.err.println("Erro ao recomendar personalizado: " + e.getMessage());
        }
        return "Nenhuma recomendação personalizada encontrada.";
    }

    private String buscarCategoriaPorObra(String nomeArquivo, String titulo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5 && partes[0].equalsIgnoreCase(titulo)) {
                    return partes[4]; // Retorna a categoria da obra
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar categoria por obra: " + e.getMessage());
        }
        return null; // Retorna null se a obra não for encontrada
    }

    private String encontrarCategoriaMaisFrequente(Map<String, Integer> categoriasContagem) {
        String categoriaMaisFrequente = null;
        int contagemMaxima = 0;

        for (Map.Entry<String, Integer> entry : categoriasContagem.entrySet()) {
            if (entry.getValue() > contagemMaxima) {
                contagemMaxima = entry.getValue();
                categoriaMaisFrequente = entry.getKey();
            }
        }

        return categoriaMaisFrequente;
    }

    private String encontrarCursoUsuario(BufferedReader usuariosReader, String idUsuario) throws IOException {
        String linha;
        while ((linha = usuariosReader.readLine()) != null) {
            String[] partesUsuario = linha.split(";");
            if (partesUsuario.length == 5 && partesUsuario[2].equals(idUsuario)) {
                return partesUsuario[3]; // Retorna o curso do usuário
            }
        }
        return null; 
    }
    
    
    public String recomendacaoCurso(String idUsuario) {
        try (BufferedReader usuariosReader = new BufferedReader(new FileReader("usuarios.txt"))) {
            // Encontrar o curso do usuário
            String cursoUsuario = encontrarCursoUsuario(usuariosReader, idUsuario);
    
            if (cursoUsuario != null) {
                
                Map<String, Integer> pesosPorCurso = obterPesosPorCurso(cursoUsuario);
    
                // Buscar e recomendar a obra com base no interesse do curso
                String recomendacao = buscarObraPorCursoEPesos(cursoUsuario, pesosPorCurso);
                if (recomendacao != null) {
                    return recomendacao;
                }
            }
    
        } catch (IOException e) {
            System.err.println("Erro ao recomendar por curso: " + e.getMessage());
        }
        return "Nenhuma recomendação por curso encontrada.";
    }
    
    private Map<String, Integer> obterPesosPorCurso(String curso) {
        Map<String, Integer> pesos = new HashMap<>();
        switch (curso) {
            case "Engenharia de Software":
                pesos.put("Tecnologia", 4);
                pesos.put("Informacao", 3);
                pesos.put("Biologia", 2);
                pesos.put("Historia", 1);
                break;
            case "Direito":
                pesos.put("Historia", 4);
                pesos.put("Informacao", 3);
                pesos.put("Tecnologia", 2);
                pesos.put("Biologia", 1);
                break;
            case "Jornalismo":
                pesos.put("Informacao", 4);
                pesos.put("Historia", 3);
                pesos.put("Tecnologia", 2);
                pesos.put("Biologia", 1);
                break;
            case "Medicina":
                pesos.put("Biologia", 4);
                pesos.put("Tecnologia", 3);
                pesos.put("Informacao", 2);
                pesos.put("Historia", 1);
                break;
            default:
                break;
        }
        return pesos;
    }
    
    private String buscarObraPorCursoEPesos(String curso, Map<String, Integer> pesos) {
        String[] arquivos = {"livros.txt", "revistas.txt", "teses.txt", "cds.txt", "dvds.txt"};
    
        String melhorObra = null;
        int melhorPontuacao = 0;
    
        for (String arquivo : arquivos) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(";");
                    if (partes.length == 5 && partes[4].equalsIgnoreCase(curso)) {
                        // Calcular a pontuação da obra com base nos pesos
                        int pontuacao = calcularPontuacaoObra(partes[4], pesos);
                        if (pontuacao > melhorPontuacao) {
                            melhorPontuacao = pontuacao;
                            melhorObra = linha;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao buscar obra por curso e pesos: " + e.getMessage());
            }
        }
    
        return melhorObra; 
    }
    
    private int calcularPontuacaoObra(String categoria, Map<String, Integer> pesos) {
        return pesos.getOrDefault(categoria, 0);
    }
    
    

    public void recomendacao() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID do usuário: ");
            String idUsuario = scanner.nextLine();
    
            Set<String> recomendacoes = new HashSet<>();
    
            // Adiciona recomendacaoPersonalizada no início
            String recomendacaoPersonalizada = recomendacaoPersonalizada(idUsuario);
        if (recomendacaoPersonalizada != null && !recomendacaoPersonalizada.equals("Nenhuma recomendação personalizada encontrada.")) {
            recomendacoes.add(recomendacaoPersonalizada);
        }
    
            // Adiciona recomendacaoCurso no início
            String recomendacaoCurso = recomendacaoCurso(idUsuario);
            if (!recomendacaoCurso.equals("Nenhuma recomendação por curso encontrada.")) {
                recomendacoes.add(recomendacaoCurso);
            }
    
            // Adaptação para a lista de recomendações
            List<String> recomendacoesInteresse = recomendacaoInteresse(idUsuario);
            recomendacoes.addAll(recomendacoesInteresse);
    
            // Imprime apenas 5 recomendações
            System.out.println("Obras Recomendadas:");
            if (!recomendacoes.isEmpty()) {
                recomendacoes.stream().limit(3).forEach(System.out::println);
            } else {
                System.out.println("Nenhuma recomendação encontrada.");
            }
    
            scanner.close();
    
        } catch (Exception e) {
            System.err.println("Erro ao obter recomendações: " + e.getMessage());
        }
    }
    
}
    