package agenda.modelo;

import jakarta.persistence.Entity;

@Entity
public class ContatoComercial extends Contato {
    private String empresa;

    public ContatoComercial() {
        super();
    }

    public ContatoComercial(String nome, String empresa, Cidade cidade) {
        super(nome, cidade);
        this.empresa = empresa;
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    @Override
    public String toString() {
        return "ContatoComercial [id=" + getId() + ", nome=" + getNome() + 
               ", cidade=" + getCidade().getNome() + ", empresa=" + empresa + 
               ", telefones=" + getTelefones() + "]";
    }
}