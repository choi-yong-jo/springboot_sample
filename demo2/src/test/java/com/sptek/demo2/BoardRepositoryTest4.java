package com.sptek.demo2;

import javax.persistence.EntityManager;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static com.sptek.demo2.QBoard.board;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest4 {

    @Autowired
    public EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void testData(){
        for(int i=0; i<100; i++) {
            Board board = new Board();
            board.setBno((long) i);
            board.setTitle("title" + i);
            board.setContent("content" + i);
            board.setWriter("writer" + (i%5));
            board.setViewCnt((int)Math.random()*100);
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardRepository.save(board);
        }
    }

    @Test
    @DisplayName("querydsl로 SQL 작성 테스트1 - 간단한 쿼리작성")
    public void querydslTest1() {
        // 1. JPAQueryFactory 생성
        JPAQueryFactory qf = new JPAQueryFactory(em);

        // 2. 쿼리 작성
        JPAQuery<Board> query = qf.selectFrom(board)
                    .where(board.title.eq("title1"));

        // 3. 쿼리 실행
        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl로 SQL 작성 테스트2 - 복잡한 쿼리작성")
    public void querydslTest2() {
        JPAQueryFactory qf = new JPAQueryFactory(em);

        JPAQuery<Tuple> query = qf.select(board.writer, board.viewCnt.sum()).from(board)
                .where(board.title.notLike("title2%"))
                .where(board.writer.eq("writer1"))
                .where(board.content.contains("content1"))
                .where(board.content.isNotNull())
                .groupBy(board.writer)
//                .having(board.viewCnt.sum().gt(100))   // 조회수가 100이 넘는 게시물
                .orderBy(board.writer.asc())
                .orderBy(board.viewCnt.sum().desc());

        List<Tuple> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl로 SQL 작성 테스트3 - 동적 쿼리작성")
    public void querydslTest3() {
        String searchBy = "TC";     // 제목(title)과 작성내용(content)에서 검색
        String keyword = "1";
        keyword = "%" + keyword + "%";

        BooleanBuilder builder = new BooleanBuilder();

        if (searchBy.equalsIgnoreCase("T")){
            builder.and(board.title.like(keyword));
        } else if (searchBy.equalsIgnoreCase("C")) {
            builder.and(board.content.like(keyword));
        } else if (searchBy.equalsIgnoreCase("TC")) {
            builder.and(board.title.like(keyword).or(board.content.like(keyword)));
        }

        JPAQueryFactory qf = new JPAQueryFactory(em);
        JPAQuery query = qf.selectFrom(board)
                .where(builder)
                .orderBy(board.upDate.desc());

        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }


}