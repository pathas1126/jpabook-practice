package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook-practice");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            Member member1 = new Member();
            member1.setName("himan");
            member1.setCity("city");
            member1.setStreet("street");
            member1.setZipcode("93511");
            em.persist(member1);

            Order order1 = new Order();
            order1.setMember(member1);
            em.persist(order1);

            Order order2 = new Order();
            order2.setMember(member1);
            em.persist(order2);

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrder(order1);
            orderItem1.setOrder(order2);
            em.persist(orderItem1);



            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

}
