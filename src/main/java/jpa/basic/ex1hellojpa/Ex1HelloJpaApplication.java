package jpa.basic.ex1hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Ex1HelloJpaApplication {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("hongmo");
            em.persist(member);

            em.flush();
            em.clear();

            final Member findMember = em.getReference(Member.class, member.getMemberId());
            System.out.println("isLoaded ? -> " + emf.getPersistenceUnitUtil().isLoaded(findMember));
            Hibernate.initialize(findMember);
            System.out.println("isLoaded ? -> " + emf.getPersistenceUnitUtil().isLoaded(findMember));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
