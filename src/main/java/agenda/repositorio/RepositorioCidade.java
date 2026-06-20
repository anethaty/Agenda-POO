package agenda.repositorio;

import agenda.modelo.Cidade;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class RepositorioCidade extends Repositorio<Cidade> {

    public RepositorioCidade() {
        super(Cidade.class); 
    }

    public Cidade localizarPorNome(String nome) {
        try {
            String jpql = "SELECT c FROM Cidade c WHERE c.nome = :n";
            TypedQuery<Cidade> query = manager.createQuery(jpql, Cidade.class);
            query.setParameter("n", nome.toUpperCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }
}