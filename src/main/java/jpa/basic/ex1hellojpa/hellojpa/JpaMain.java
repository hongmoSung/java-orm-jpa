package jpa.basic.ex1hellojpa.hellojpa;

import org.hibernate.Hibernate;

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
            Member member = new Member();
            member.setName("user1");
            em.persist(member);

            em.flush();
            em.clear();

//            final Member findMember = em.find(Member.class, member.getId());
            final Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember -> " + refMember.getClass());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(refMember));

//            em.detach(refMember);
            Hibernate.initialize(refMember);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(refMember));

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
