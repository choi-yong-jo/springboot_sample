package com.sptek.demo2;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest3 {

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
    @DisplayName("@Query로 JPQL 작성 테스트")
    public void queryAnnoTest() {
        List<Board> list = boardRepository.findAllBoard();
        assertTrue(list.size() <= 100);
    }

    @Test
    @DisplayName("@Query로 JPQL 작성 테스트 - 매개변수 순서(ByOrder)")
    public void queryAnnoTest2() {
        List<Board> list = boardRepository.findByOrder("title1","writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() <= 1);
    }

    @Test
    @DisplayName("@Query로 JPQL 작성 테스트 - 매개변수 이름(ByName)")
    public void queryAnnoTest3() {
        List<Board> list = boardRepository.findByName("title1","writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() <= 1);
    }

    @Test
    @DisplayName("@Query로 네이티브 SQL 작성 테스트")
    public void queryAnnoTest4() {
        List<Board> list = boardRepository.findAllBoardBySQL();
        assertTrue(list.size() <= 100);
    }

    @Test
    @DisplayName("@Query로 네이티브 SQL 작성 테스트2")
    public void queryAnnoTest5() {
        List<Object[]> list = boardRepository.findAllBoardBySQL2();
//        list.forEach(System.out::println);
        list.stream()   // list를 stream으로 변환
                .map(arr -> Arrays.toString(arr))   // arr를 String으로 변환
                .forEach(System.out::println);
        assertTrue(list.size() <= 100);
    }

    @Test
    @DisplayName("@Query로 네이티브 SQL 작성 테스트 3 - 페이징과 정렬")
    public void queryAnnoTestPagingAndSort() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "view_cnt");
        List<Board> list = boardRepository.findAllBoardBySQL3(pageable);
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("createQuery로 JPQL 작성 테스트")
    public void createQueryTest(){
        String query = "select b from Board b where 1=1";
        TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
        List<Board> list = tQuery.getResultList();

        list.forEach(System.out::println);
        assertTrue(list.size() <= 100);
    }



}