package agenda.repositorio;

import agenda.modelo.ContatoPessoal;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class RepositorioContatoPessoal extends Repositorio<ContatoPessoal> {

    public RepositorioContatoPessoal() {
        super(ContatoPessoal.class);
    }

    public ContatoPessoal localizarPorNome(String nome) {
        try {
            String jpql = "SELECT c FROM ContatoPessoal c WHERE c.nome = :n";
            TypedQuery<ContatoPessoal> query = manager.createQuery(jpql, ContatoPessoal.class);
            query.setParameter("n", nome.toUpperCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}