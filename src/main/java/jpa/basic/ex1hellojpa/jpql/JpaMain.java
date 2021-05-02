package jpa.basic.ex1hellojpa.jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("홍성모" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            final List<Member> result =
                    em.createQuery("select m from Member m order by m.age asc ", Member.class)
                            .setFirstResult(10)
                            .setMaxResults(5)
                            .getResultList();

            for (Member member1 : result) {
                System.out.println(member1.toString());
            }
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
