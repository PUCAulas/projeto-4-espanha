package src;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testPesquisaPorTitulo() {
        String input = "Livro 1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorTitulo();

        String expectedOutput = "Digite o título da obra que deseja pesquisar: Resultado da pesquisa por título: Livro 1";

        assertEquals(expectedOutput.trim(), outputContent.toString().trim());
    }

    @Test
    public void testPesquisaPorTituloArquivo() {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorTituloArquivo("../livros.txt", "Livro 2", "Livro");

        String expectedOutput = "Tipo: Livro\n" +
                "Título: Livro 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2001\n" +
                "Exemplares Disponíveis: 2\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));

    }

    @Test
    void testPesquisaPorAutor() {
        String input = "Autor 1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorAutor();

        String expectedOutput = "Digite o nome do autor que deseja pesquisar: Resultado da pesquisa por autor: Autor 1";

        assertEquals(expectedOutput.trim(), outputContent.toString().trim());

    }

    @Test
    void testPesquisaPorAutorArquivo() {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorAutorArquivo("../livros.txt", "Autor 1", "Livro");
        biblioteca.pesquisaPorAutorArquivo("../revistas.txt", "Autor 1", "Revista");

        String expectedOutput = "Tipo: Livro\n" +
                "Título: Livro 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2000\n" +
                "Exemplares Disponíveis: 3\n" +
                "Tipo: Revista\n" +
                "Título: Revista 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2003\n" +
                "Exemplares Disponíveis: 3\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaPorAno() {
        String input = "2000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorAnoPublicacao();

        String expectedOutput = "Digite o ano de publicação que deseja pesquisar: Resultado da pesquisa por ano de publicação: 2000";

        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));

    }

    @Test
    void testPesquisaPorAnoArquivo() {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.pesquisaPorAnoPublicacaoArquivo("../livros.txt", 2000, "Livro");
        biblioteca.pesquisaPorAnoPublicacaoArquivo("../revistas.txt", 2000, "Revista");

        String expectedOutput = "Tipo: Livro\n" +
                "Título: Livro 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2000\n" +
                "Exemplares Disponíveis: 3\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaTipoLivro() {

        Biblioteca biblioteca = new Biblioteca();

        String filePath = "../livros.txt";

        biblioteca.pesquisaTipoLivro(filePath);

        String expectedOutput = "Livros em ordem alfabética por título:\n" +
                "Título: Livro 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2000\n" +
                "Exemplares Disponíveis: 3\n" +

                "Título: Livro 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2001\n" +
                "Exemplares Disponíveis: 2\n" +

                "Título: Livro 3\n" +
                "Autor: Autor 3\n" +
                "Ano de Publicação: 2002\n" +
                "Exemplares Disponíveis: 2\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaTipoRevista() {
        Biblioteca biblioteca = new Biblioteca();

        String filePath = "../revistas.txt";

        biblioteca.pesquisaTipoRevista(filePath);

        String expectedOutput = "Revistas em ordem alfabética por título:\n" +
                "Título: Revista 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2003\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: Revista 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2004\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: Revista 3\n" +
                "Autor: Autor 3\n" +
                "Ano de Publicação: 2005\n" +
                "Exemplares Disponíveis: 3\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaTipoTese() {
        Biblioteca biblioteca = new Biblioteca();

        String filePath = "../teses.txt";

        biblioteca.pesquisaTipoTese(filePath);

        String expectedOutput = "Teses em ordem alfabética por título:\n" +
                "Título: Tese 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2006\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: Tese 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2007\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: Tese 3\n" +
                "Autor: Autor 3\n" +
                "Ano de Publicação: 2008\n" +
                "Exemplares Disponíveis: 3\n";
        ;
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaTipoCD() {
        Biblioteca biblioteca = new Biblioteca();

        String filePath = "../cds.txt";

        biblioteca.pesquisaTipoCD(filePath);

        String expectedOutput = "CDs em ordem alfabética por título:\n" +
                "Título: CD 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2009\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: CD 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2010\n" +
                "Exemplares Disponíveis: 3\n" +
                "Título: CD 3\n" +
                "Autor: Autor 3\n" +
                "Ano de Publicação: 2011\n" +
                "Exemplares Disponíveis: 3\n";
        ;
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

    @Test
    void testPesquisaTipoDVD() {
        Biblioteca biblioteca = new Biblioteca();
        String filePath = "../dvds.txt";

        biblioteca.pesquisaTipoDVD(filePath);

        String expectedOutput = "DVDs em ordem alfabética por título:\n" +
                "Título: DVD 1\n" +
                "Autor: Autor 1\n" +
                "Ano de Publicação: 2012\n" +
                "Exemplares Disponíveis: 2\n" +
                "Título: DVD 2\n" +
                "Autor: Autor 2\n" +
                "Ano de Publicação: 2013\n" +
                "Exemplares Disponíveis: 2\n" +
                "Título: DVD 3\n" +
                "Autor: Autor 3\n" +
                "Ano de Publicação: 2014\n" +
                "Exemplares Disponíveis: 2\n";
        assertEquals(expectedOutput.trim().replaceAll("\\s+", ""),
                outputContent.toString().trim().replaceAll("\\s+", ""));
    }

}