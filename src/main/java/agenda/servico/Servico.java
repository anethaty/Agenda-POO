package agenda.servico;

import agenda.util.Util;

public class Servico {
    
    public static void inicializar() {
        Util.conectarBanco();
    }
    
    public static void finalizar() {
        Util.fecharBanco();
    }
}