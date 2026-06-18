package agenda;

import agenda.modelo.Cidade;
import agenda.modelo.ContatoPessoal;
import agenda.util.Util;
import jakarta.persistence.EntityManager;

public class TesteCriacaoTabelas {
    public static void main(String[] args) {
        try {
            EntityManager manager = Util.conectarBanco();
            
            manager.getTransaction().begin();
            
            Cidade joaoPessoa = new Cidade("João Pessoa");
            manager.persist(joaoPessoa); 
            
            ContatoPessoal contato = new ContatoPessoal("Thatyane Alves", 3, joaoPessoa);
            contato.adicionarTelefone("83999998888");
            
            manager.persist(contato); 
            
            manager.getTransaction().commit();
            
            System.out.println("Sucesso! Verifique o pgAdmin. As tabelas foram geradas e os dados salvos.");
            
        } catch (Exception e) {
            System.err.println("Erro na configuração ou conexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Util.fecharBanco();
        }
    }
}