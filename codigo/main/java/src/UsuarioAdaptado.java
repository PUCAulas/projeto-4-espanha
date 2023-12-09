package src;

import java.util.Set;

public class UsuarioAdaptado extends Usuario implements UsuarioAdapter {
    private String curso;
    private Set<String> categoriasDeInteresse;

    public UsuarioAdaptado(String nome, String cpf, int id, String curso, Set<String> categoriasDeInteresse) {
        super(nome, cpf, id);
        this.curso = curso;
        this.categoriasDeInteresse = categoriasDeInteresse;
    }

    @Override
    public String getCurso() {
        return curso;
    }

    @Override
    public Set<String> getCategoriasDeInteresse() {
        return categoriasDeInteresse;
    }
}
