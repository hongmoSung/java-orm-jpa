package jpa.basic.ex1hellojpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setId(1L);
            member1.setName("hello");
            em.persist(member1);
            // 준영속성
//            em.detach(member1);
            tx.commit();

            final Member findMember = em.find(Member.class, 1L);
            findMember.setName("bye");

            tx.begin();
            em.persist(findMember);
            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
