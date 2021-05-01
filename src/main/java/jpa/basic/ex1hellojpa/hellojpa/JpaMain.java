package jpa.basic.ex1hellojpa.hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            Team team2 = new Team();
            team.setName("teamB");

            em.persist(team);
            em.persist(team2);

            Member member = new Member();
            member.setName("user1");
            member.setTeam(team);

            Member member2 = new Member();
            member.setName("user2");
            member.setTeam(team2);

            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear();

//            final Member m = em.find(Member.class, member.getId());
            final List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

        } catch (Exception e) {
            tx.commit();
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
