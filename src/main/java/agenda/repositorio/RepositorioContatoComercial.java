package agenda.repositorio;

import agenda.modelo.ContatoComercial;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class RepositorioContatoComercial extends Repositorio<ContatoComercial> {

    public RepositorioContatoComercial() {
        super(ContatoComercial.class);
    }

    public ContatoComercial localizarPorNome(String nome) {
        try {
            String jpql = "SELECT c FROM ContatoComercial c WHERE c.nome = :n";
            TypedQuery<ContatoComercial> query = manager.createQuery(jpql, ContatoComercial.class);
            query.setParameter("n", nome.toUpperCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}