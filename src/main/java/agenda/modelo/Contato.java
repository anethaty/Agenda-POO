package agenda.modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contato{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String nome;
	
	@ManyToOne
	private Cidade cidade;
	
	@ElementCollection
	private List<String> telefones = new ArrayList<>();
	
	
	//construtor
	
	public Contato() {
		
	}
	
	public Contato(String nome, Cidade cidade) {
		this.nome = nome.toUpperCase();
		this.cidade = cidade;
	}
	
	
	//Gets e Setters
	
	public int getId() {return this.Id;}
	public void serId(int Id) {this.Id = Id;}
	
	public String getNome() {return this.nome;}
	public void setnome(String nome) {this.nome = nome.toUpperCase();}
	
	public Cidade getCidade() {return this.cidade;}
	public void setcidade(Cidade cidade) {this.cidade = cidade;}
	
	public List<String> getTelefones() {return this.telefones;}
	public void setTelefone(List<String> telefones) {this.telefones = telefones;}
	
	//metodos
	public void adicionarTelefone(String numero) {
		if(!this.telefones.contains(numero)){
			this.telefones.add(numero);
		}
	}
	
}










