package agenda.servico;

import agenda.modelo.Cidade;
import agenda.repositorio.RepositorioCidade;

public class ServicoCidade {
    
    private static RepositorioCidade repCidade = new RepositorioCidade();

    public static void criarCidade(String nome) throws Exception {
        nome = nome.toUpperCase(); 
        
        if (repCidade.localizarPorNome(nome) != null) {
            throw new Exception("Regra de Negócio: Já existe uma cidade com este nome.");
        }
        
        Cidade c = new Cidade(nome);
        repCidade.create(c);
    }

    public static Cidade localizarCidade(int id) {
        return repCidade.read(id);
    }

    public static Cidade localizarCidade(String nome) {
        return repCidade.localizarPorNome(nome);
    }
}