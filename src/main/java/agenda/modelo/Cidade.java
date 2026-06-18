package agenda.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nome;

    public Cidade() {}

    public Cidade(String nome) {
        this.nome = nome.toUpperCase(); 
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.toUpperCase(); }

    @Override
    public String toString() {
        return "Cidade [id=" + id + ", nome=" + nome + "]";
    }
}