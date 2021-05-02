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
            Member member = new Member();
            member.setUsername("홍성모");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            final List<MemberDTO> result = em.createQuery("select new jpa.basic.ex1hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
            final MemberDTO dto = result.get(0);
            System.out.println(dto.getUsername());
            System.out.println(dto.getAge());
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
