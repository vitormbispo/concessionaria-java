package concessionaria.dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Armazena os objetos de gerenciamento de entidades do banco de dados.
 */
public class BancoDados {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    
    /**
     * Inicia os gerenciadores de entidade
     */
    public static void iniciar() {
        emf = Persistence.createEntityManagerFactory("ConcessionariaPU");
        em = emf.createEntityManager();
    }
    
    /**
     * Libera os recursos dos gerenciadores de entidade
     */
    public static void encerrar() {
        em.close();
        emf.close();
    }
}
