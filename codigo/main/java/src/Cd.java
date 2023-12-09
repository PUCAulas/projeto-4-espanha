package src;
public class Cd extends ItemBiblioteca {

    public Cd(String titulo, String autor, int anoPublicacao, int exemplaresDisponiveis, String categoria) {
        super(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
    }

    @Override
    public String getTipo() {
        return "CD";
    }

    @Override
    public boolean isEmprestavel() {
        return true;
    }
}