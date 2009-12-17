/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatestcase;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author gustavoduarte
 */
public class Main {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATestCasePU");

    private static void testCascade() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Users u = new Users("Mr X");
            em.persist(u);
            Groups g = new Groups("Tiny Toon");
            em.persist(g);
            UserGroup ug = new UserGroup(u.getId(), g.getId());
            ug.setSince(new Date());
            em.persist(ug);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("CRAP!!!");
            }
        }

        /////////////////////////////////////////////////////
        if (false) return;
        
        em.clear();
        try {
            tx.begin();
            Users u = (Users) ((List) em.createQuery("select u from Users u").getResultList()).get(0);
            em.remove(u);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("OUCH!!!");
            }
            em.close();
        }
    }

    public static void main(String[] args) {
        testCascade();
    }
}
