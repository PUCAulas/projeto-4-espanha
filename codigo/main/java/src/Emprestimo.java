package src;
public interface Emprestimo {
    void emprestar(); // Método para emprestar um item da biblioteca.
    void devolver();  // Método para devolver um item à biblioteca.
    boolean verificarDisponibilidade(String tituloItem);  // Método para verificar a disponibilidade do item.
}
