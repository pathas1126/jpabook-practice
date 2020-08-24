package jpabook.model;

import jpabook.model.entity.*;

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


            Category category1 = new Category();
            category1.setName("fruits");
            em.persist(category1);

            Category category2 = new Category();
            category2.setName("apples");
            category2.setParent(category1);
            em.persist(category2);

            Item item1 = new Item();
            item1.setName("hi");
            item1.setPrice(500);
            em.persist(item1);

            Item item2 = new Item();
            item2.setName("hello");
            item2.setPrice(5000);
            em.persist(item2);

            ItemCategory itemCategory1 = new ItemCategory();
            itemCategory1.setItem(item1);
            itemCategory1.setCategory(category1);
            em.persist(itemCategory1);

            ItemCategory itemCategory2 = new ItemCategory();
            itemCategory2.setItem(item2);
            itemCategory2.setCategory(category1);
            em.persist(itemCategory2);

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
