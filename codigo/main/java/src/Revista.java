package src;
public class Revista extends ItemBiblioteca {

    public Revista(String titulo, String autor, int anoPublicacao, int exemplaresDisponiveis, String categoria) {
        super(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
    }


    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public boolean isEmprestavel() {
        return false;
    }
}