package jpa.basic.ex1hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Ex1HelloJpaApplication {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Team team = new Team();
            team.setTeamName("비즈웨이브");
            em.persist(team);

            Member member = new Member();
            member.setUsername("홍모");
            member.setTeam(team);
            em.persist(member);

//            em.flush();
            em.clear();

            final Member foundedMember = em.find(Member.class, member.getMemberId());
            System.out.println(foundedMember.getTeam().getTeamName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
