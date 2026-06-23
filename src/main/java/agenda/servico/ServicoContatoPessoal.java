package agenda.servico;

import java.util.List;
import agenda.modelo.Cidade;
import agenda.modelo.Contato;
import agenda.modelo.ContatoPessoal;
import agenda.repositorio.RepositorioCidade;
import agenda.repositorio.RepositorioContato;
import agenda.repositorio.RepositorioContatoPessoal;

public class ServicoContatoPessoal {

    private static RepositorioContatoPessoal repPessoal = new RepositorioContatoPessoal();
    private static RepositorioCidade repCidade = new RepositorioCidade();
    private static RepositorioContato repContato = new RepositorioContato(); 

    public static void criarContatoPessoal(String nome, int grauProximidade, int idcidade) throws Exception {
        nome = nome.toUpperCase();  
        
        if (repContato.localizarPorNome(nome) != null) {
            throw new Exception("Já existe um contato com este nome na agenda.");
        }
        if (grauProximidade < 1 || grauProximidade > 3) {
            throw new Exception("O grau de proximidade deve ser 1 (baixo), 2 (médio) ou 3 (alto).");  
        }
        
        Cidade cidade = repCidade.read(idcidade);
        if (cidade == null) {
            throw new Exception("Todo contato tem que se relacionar com uma cidade existente."); 
        }
        
        ContatoPessoal cp = new ContatoPessoal(nome, grauProximidade, cidade);
        repPessoal.create(cp);
    }

    public static ContatoPessoal localizarContatoPessoal(int id) {
        return repPessoal.read(id);
    }

    public static ContatoPessoal localizarContatoPessoal(String nome) {
        return repPessoal.localizarPorNome(nome);
    }

    public static void alterarContatoPessoal(int id, String nome, int grauProximidade, int idcidade) throws Exception {
        ContatoPessoal cp = repPessoal.read(id);
        if (cp == null) throw new Exception("Contato não encontrado.");

        nome = nome.toUpperCase();
        Contato existente = repContato.localizarPorNome(nome);
        
        if (existente != null && existente.getId() != id) {
            throw new Exception("Já existe outro contato com este nome.");
        }
        if (grauProximidade < 1 || grauProximidade > 3) {
            throw new Exception("O grau de proximidade deve ser 1, 2 ou 3.");
        }
        
        Cidade cidade = repCidade.read(idcidade);
        if (cidade == null) throw new Exception("Cidade inexistente.");

        cp.setNome(nome);
        cp.setGrauProximidade(grauProximidade);
        cp.setCidade(cidade);
        repPessoal.update(cp);
    }

    public static List<ContatoPessoal> listarContatosPessoais() {
        return repPessoal.readAll();
    }
}