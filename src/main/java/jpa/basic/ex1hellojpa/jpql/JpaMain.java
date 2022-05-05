package jpa.basic.ex1hellojpa.jpql;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            entityManager.persist(parent);
            entityManager.flush();
            entityManager.clear();

            Parent result = entityManager.createQuery("select p from Parent p join fetch p.childList where p.id = " + child1.getId(), Parent.class)
                    .getSingleResult();

            List<Child> childList = result.getChildList();
            Child child = entityManager.find(Child.class, child1.getId());
            boolean remove = childList.remove(child);
            System.out.println("remove = " + remove);


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
