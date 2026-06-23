package agenda;

import agenda.modelo.Cidade;
import agenda.modelo.ContatoPessoal;
import agenda.modelo.ContatoComercial;
import agenda.servico.Servico;
import agenda.servico.ServicoCidade;
import agenda.servico.ServicoContato;
import agenda.servico.ServicoContatoPessoal;
import agenda.servico.ServicoContatoComercial;



//Luis aqui é só um teste pra ver se tá funcionando, depois pode apagar


public class TesteCriacaoTabelas {
    public static void main(String[] args) {
        try {
            System.out.println("====== INICIANDO TESTES DA AGENDA ======\n");
            
            // 1. Inicializa o motor do banco e do Hibernate
            Servico.inicializar();
            System.out.println("[OK] Banco de dados conectado com sucesso.");

            // ==========================================
            // TESTE 1: CRIANDO CIDADES
            // ==========================================
            System.out.println("\n--- Testando Cadastro de Cidades ---");
            ServicoCidade.criarCidade("João PessoaA");
            ServicoCidade.criarCidade("Campina Grande");
            System.out.println("[OK] Cidades cadastradas (Verifique se ficaram em MAIÚSCULO no banco).");

            // Recupera as cidades criadas para usar nos contatos
            Cidade jp = ServicoCidade.localizarCidade("JOÃO PESSOAA");
            Cidade cg = ServicoCidade.localizarCidade("CAMPINA GRANDE");

            // ==========================================
            // TESTE 2: CRIANDO CONTATOS
            // ==========================================
            System.out.println("\n--- Testando Cadastro de Contatos ---");
            
            // Cadastro Pessoal (Grau 3 = Alto)
            ServicoContatoPessoal.criarContatoPessoal("Thatyane", 3, jp.getId());
            
            // Cadastro Comercial
            ServicoContatoComercial.criarContatoComercial("Luís Miguel", "Unisigma", cg.getId());
            
            System.out.println("[OK] Contatos cadastrados com sucesso.");

            // ==========================================
            // TESTE 3: REGRAS DE NEGÓCIO (Forçando Erros)
            // ==========================================
            System.out.println("\n--- Testando Validação das Regras de Negócio ---");
            
            // Tentando cadastrar contato com o mesmo nome (Regra 1)
            try {
                ServicoContatoPessoal.criarContatoPessoal("Thatyane", 2, jp.getId());
                System.err.println("[ERRO] A regra de nome duplicado FALHOU! Deixou cadastrar.");
            } catch (Exception e) {
                System.out.println("[OK] Regra 1 Funcionou: " + e.getMessage());
            }

            // Tentando cadastrar proximidade inválida (Regra 5)
            try {
                ServicoContatoPessoal.criarContatoPessoal("Andrew Silva", 5, jp.getId());
                System.err.println("[ERRO] A regra de proximidade inválida FALHOU!");
            } catch (Exception e) {
                System.out.println("[OK] Regra 5 Funcionou: " + e.getMessage());
            }

            // ==========================================
            // TESTE 4: ADICIONANDO TELEFONES E PARTE GLOBAL
            // ==========================================
            System.out.println("\n--- Testando Telefones ---");
            
            // Busca o ID da Thati que foi gerado no banco
            ContatoPessoal thati = ServicoContatoPessoal.localizarContatoPessoal("THATYANE");
            
            // Adiciona dois telefones diferentes
            ServicoContato.adicionarTelefoneContato("83999998888", thati.getId());
            ServicoContato.adicionarTelefoneContato("83988887777", thati.getId());
            System.out.println("[OK] Telefones adicionados com sucesso.");

            // Tentando adicionar telefone igual (Regra 6)
            try {
                ServicoContato.adicionarTelefoneContato("83999998888", thati.getId());
                System.err.println("[ERRO] A regra de telefones duplicados FALHOU!");
            } catch (Exception e) {
                System.out.println("[OK] Regra 6 Funcionou: " + e.getMessage());
            }

            // ==========================================
            // TESTE 5: LISTAGEM NO CONSOLE
            // ==========================================
            System.out.println("\n--- Listagem Final dos Dados ---");
            System.out.println("Contatos Pessoais cadastrados:");
            ServicoContatoPessoal.listarContatosPessoais().forEach(System.out::println);
            
            System.out.println("\nContatos Comerciais cadastrados:");
            ServicoContatoComercial.listarContatosEmpresa().forEach(System.out::println);

            System.out.println("\n====== TESTES FINALIZADOS COM SUCESSO ======");
            
        } catch (Exception e) {
            System.err.println("\n[ERRO FATAL EM ALGO DO SISTEMA]: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Desconecta do banco de dados com segurança
            Servico.finalizar();
        }
    }
}