package jpa.basic.ex1hellojpa.jpql;

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

            final Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

            final Member member = new Member();
            member.setUserName("hongmo");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            final Member findMember = em.find(Member.class, member.getMemberId());
            final Team memberTeam = findMember.getTeam();
            System.out.println(memberTeam.getName());
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
