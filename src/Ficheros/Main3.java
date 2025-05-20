package Ficheros;
import ProyectoFinalProgramacion.GestionUsuarios;
import ProyectoFinalProgramacion.Usuario;
import ProyectoFinalProgramacion.utils.GestionFicheros;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

public class Main3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("objectdb:$objectdb/db/proyectoFinal.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        GestionUsuarios gestion2 = new GestionUsuarios(

        );
        Usuario u3 = new Usuario("Paco", "abc123hashed");
        Usuario u4 = new Usuario("Seryi", "xyz456hashed");

        gestion2.insertarUsuario(u3);
        gestion2.insertarUsuario(u4);

        em.persist(gestion2);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
