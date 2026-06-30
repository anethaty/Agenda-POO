package agenda.servico;

import agenda.modelo.Contato;
import agenda.repositorio.RepositorioContato;

public class ServicoContato {
    
    private static RepositorioContato repContato = new RepositorioContato();

    public static void adicionarTelefoneContato(String numero, int id) throws Exception {
        Contato c = repContato.read(id);
        if (c == null) throw new Exception("Contato não encontrado.");

        if (c.getTelefones().contains(numero)) {
            throw new Exception("Um contato não pode ter dois telefones iguais."); 
        }
        
        c.getTelefones().add(numero);
        repContato.update(c);
    }

    public static void apagarContato(int id) throws Exception {
        Contato c = repContato.read(id);
        if (c == null) throw new Exception("Contato não encontrado.");
        
        repContato.delete(c); 
    }
    
    
}