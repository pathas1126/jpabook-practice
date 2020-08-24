package jpabook.model;

import jpabook.model.entity.*;
import jpabook.model.entity.item.Album;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Item;
import jpabook.model.entity.item.Movie;

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

            Album album = new Album();
            album.setArtist("ME");
            em.persist(album);

            Book book = new Book();
            book.setAuthor("ME");
            em.persist(book);

            Movie movie = new Movie();
            movie.setDirector("ME");
            em.persist(movie);


            Category category1 = new Category();
            category1.setName("MOVIE");
            em.persist(category1);

            Category category2 = new Category();
            category2.setName("ALBUM");
            category2.setParent(category1);
            em.persist(category2);

            ItemCategory itemCategory1 = new ItemCategory();
            itemCategory1.setItem(album);
            itemCategory1.setCategory(category1);
            em.persist(itemCategory1);

            ItemCategory itemCategory2 = new ItemCategory();
            itemCategory2.setItem(movie);
            itemCategory2.setCategory(category2);
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
