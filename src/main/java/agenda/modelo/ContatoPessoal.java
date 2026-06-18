package agenda.modelo;

import jakarta.persistence.Entity;

@Entity
public class ContatoPessoal extends Contato {
    private int grauProximidade; 

    public ContatoPessoal() {
        super();
        
    }

    public ContatoPessoal(String nome, int grauProximidade, Cidade cidade) {
        super(nome, cidade); 
        this.grauProximidade = grauProximidade;
    }

    public int getGrauProximidade() { return grauProximidade; }
    public void setGrauProximidade(int grauProximidade) { this.grauProximidade = grauProximidade; }

    @Override
    public String toString() {
        return "ContatoPessoal [id=" + getId() + ", nome=" + getNome() + 
               ", cidade=" + getCidade().getNome() + ", proximidade=" + grauProximidade + 
               ", telefones=" + getTelefones() + "]";
    }
}