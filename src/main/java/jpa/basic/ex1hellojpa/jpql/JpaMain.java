package jpa.basic.ex1hellojpa.jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Member member1 = new Member(1L, "member1");
            Member member2 = new Member(2L, "member2");
            Member member3 = new Member(3L, "member3");
            Member member4 = new Member(4L, "member4");

            entityManager.persist(member1);
            entityManager.persist(member2);
            entityManager.persist(member3);
            entityManager.persist(member4);

            System.out.println("=============== JPQL ==================");
            entityManager.createQuery("select m from Member m", Member.class).getResultList();
            System.out.println("=============== JPQL ==================");

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
