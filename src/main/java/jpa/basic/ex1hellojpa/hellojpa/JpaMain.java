package jpa.basic.ex1hellojpa.hellojpa;

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
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("서울특별시", "관악구", "9999"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressesHistory().add(new AddressEntity("경기도", "분당구", "1111"));
            member.getAddressesHistory().add(new AddressEntity("부산", "남포동", "2222"));

            em.persist(member);
            System.out.println("================start==================");
//            final Member findMember = em.find(Member.class, member.getId());
//            final Address homeAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("mewCity", homeAddress.getStreet(), homeAddress.getZipcode()));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
//            findMember.getAddressesHistory().remove(new AddressEntity("부산", "남포동", "2222"));
//            findMember.getAddressesHistory().add(new AddressEntity("newCity", "남포동", "2222"));

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
