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
            Member hi = new Member("hi");
            Member hi1 = new Member("hi1");
            Member hi2 = new Member("hi2");
            Member hi3 = new Member("hi3");
            Member hi4 = new Member("hi4");

            entityManager.persist(hi);
            entityManager.persist(hi1);
            entityManager.persist(hi2);
            entityManager.persist(hi3);
            entityManager.persist(hi4);

            System.out.println(hi);
            System.out.println(hi1);
            System.out.println(hi2);
            System.out.println(hi3);
            System.out.println(hi4);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
