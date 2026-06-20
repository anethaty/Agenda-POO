package agenda.repositorio;

import agenda.modelo.Contato;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class RepositorioContato extends Repositorio<Contato> {

    public RepositorioContato() {
        super(Contato.class);
    }

    public Contato localizarPorNome(String nome) {
        try {
            String jpql = "SELECT c FROM Contato c WHERE c.nome = :n";
            TypedQuery<Contato> query = manager.createQuery(jpql, Contato.class);
            query.setParameter("n", nome.toUpperCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}