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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("user1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            final Member m = em.find(Member.class, member.getId());
            System.out.println("=============================================");
            System.out.println(m.getName());
            System.out.println("=============================================");
            System.out.println(m.getTeam().getName());
            System.out.println("=============================================");

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
