package src;
public class Dvd extends ItemBiblioteca {

    public Dvd(String titulo, String autor, int anoPublicacao, int exemplaresDisponiveis, String categoria) {
        super(titulo, autor, anoPublicacao, exemplaresDisponiveis, categoria);
    }


    @Override
    public String getTipo() {
        return "dvd";
    }

    @Override
    public boolean isEmprestavel() {
        return true;
    }
}