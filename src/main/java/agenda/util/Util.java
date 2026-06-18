package agenda.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
    
    private static EntityManagerFactory factory;
    private static EntityManager manager;

    public static EntityManager conectarBanco() {
        if (manager == null || !manager.isOpen()) {
            factory = Persistence.createEntityManagerFactory("hibernate-postgresql");
            manager = factory.createEntityManager();
        }
        return manager;
    }

    public static void fecharBanco() {
        if (manager != null && manager.isOpen()) {
            manager.close();
            factory.close();
        }
    }
}