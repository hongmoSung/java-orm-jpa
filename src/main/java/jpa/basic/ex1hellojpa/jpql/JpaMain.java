package jpa.basic.ex1hellojpa.jpql;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

//            Member findMember = entityManager.find(Member.class, 1L);
            Member findMember = entityManager.getReference(Member.class, 1L);
//            System.out.println("isLoaded? " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember));
//            findMember.getUsername();
//            System.out.println("isLoaded2? " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember));

            Hibernate.initialize(findMember);

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
