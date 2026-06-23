package agenda.servico;

import java.util.List;
import agenda.modelo.Cidade;
import agenda.modelo.ContatoComercial;
import agenda.repositorio.RepositorioCidade;
import agenda.repositorio.RepositorioContato;
import agenda.repositorio.RepositorioContatoComercial;

public class ServicoContatoComercial {

    private static RepositorioContatoComercial repComercial = new RepositorioContatoComercial();
    private static RepositorioCidade repCidade = new RepositorioCidade();
    private static RepositorioContato repContato = new RepositorioContato();

    public static void criarContatoComercial(String nome, String empresa, int idcidade) throws Exception {
        nome = nome.toUpperCase();
        
        if (repContato.localizarPorNome(nome) != null) {
            throw new Exception("Já existe um contato com este nome na agenda.");
        }
        if (empresa == null || empresa.trim().isEmpty()) {
            throw new Exception("A empresa de um contato comercial é obrigatória."); 
        }
        
        Cidade cidade = repCidade.read(idcidade);
        if (cidade == null) {
            throw new Exception("Todo contato tem que se relacionar com uma cidade existente.");
        }
        
        ContatoComercial cc = new ContatoComercial(nome, empresa, cidade);
        repComercial.create(cc);
    }

    public static ContatoComercial localizarContatoComercial(int id) {
        return repComercial.read(id);
    }

    public static ContatoComercial localizarContatoComercial(String nome) {
        return repComercial.localizarPorNome(nome);
    }

    public static void alterarContatoComercial(String nome, String empresa, int idcidade) throws Exception {
        nome = nome.toUpperCase();
        ContatoComercial cc = repComercial.localizarPorNome(nome);
        
        if (cc == null) throw new Exception("Contato comercial não encontrado pelo nome informado.");
        if (empresa == null || empresa.trim().isEmpty()) {
            throw new Exception("A empresa é obrigatória.");
        }
        
        Cidade cidade = repCidade.read(idcidade);
        if (cidade == null) throw new Exception("Cidade inexistente.");

        cc.setEmpresa(empresa);
        cc.setCidade(cidade);
        repComercial.update(cc);
    }

    public static List<ContatoComercial> listarContatosEmpresa() {
        return repComercial.readAll();
    }
}