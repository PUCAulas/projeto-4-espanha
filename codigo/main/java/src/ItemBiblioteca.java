package src;

public abstract class ItemBiblioteca {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int exemplaresDisponiveis;
    private String categoria;

    public ItemBiblioteca(String titulo, String autor, int anoPublicacao, int exemplaresDisponiveis, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.exemplaresDisponiveis = exemplaresDisponiveis;
        this.categoria = categoria;
    }

    // Métodos getters e setters para os atributos

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public void setExemplaresDisponiveis(int exemplaresDisponiveis) {
        this.exemplaresDisponiveis = exemplaresDisponiveis;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos abstratos que as classes filhas devem implementar
    public abstract String getTipo();
    public abstract boolean isEmprestavel();
}
