package com.sptek.demo2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Demo2Application.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        SpringApplication.run(Demo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("cyjo");
        user.setPassword("1207");
        user.setName("Choi");
        user.setEmail("cyjo1207@gmail.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin();             // 트랜잭션 시작
        em.persist(user);       // user 엔티티를 em에 영속화(저장)

        user.setPassword("4321");
        user.setEmail("bbb@gmail.com");

        // 3. 조회
        User user2 = em.find(User.class, "aaa");
        System.out.println("user == user2 = " + (user == user2));
        User user3 = em.find(User.class, "bbb");
        System.out.println("user == user2 = " + (user == user3));

        // 4. 삭제
        em.remove(user);
        tx.commit();            // 트랜잭션 종료

    }
}
