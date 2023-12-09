package src;
public class Tese extends ItemBiblioteca {

    public Tese(String titulo, String autor, int anoPublicacao, int exemplaresDisponiveis, String categoria) {
        super(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
    }


    @Override
    public String getTipo() {
        return "Tese";
    }

    @Override
    public boolean isEmprestavel() {
        return false;
    }
}