package trabalhopoo1.dados;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BancoDados {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    
    public static void iniciar() {
        emf = Persistence.createEntityManagerFactory("TrabalhoPOO1PU");
        em = emf.createEntityManager();
    }
}
