package jpa.basic.ex1hellojpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<Member> query = cb.createQuery(Member.class);

            final Root<Member> m = query.from(Member.class);

            final CriteriaQuery<Member> criteriaQuery = query.select(m).where(cb.equal(m.get("username"), "kim"));
            final List<Member> members = em.createQuery(criteriaQuery).getResultList();
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
