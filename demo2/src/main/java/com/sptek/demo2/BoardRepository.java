package com.sptek.demo2;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("SELECT b FROM Board b where 1=1")     // JPQL은 명칭을 대소문자 구분에 주의
    List<Board> findAllBoard();         // 메서드 이름은 아무거나 해도 괜찮음.

    @Query("SELECT b FROM Board b WHERE b.title=?1 and b.writer=?2")
    List<Board> findByOrder(String title, String writer);

    @Query("SELECT b FROM Board b WHERE b.title=:title and b.writer=:writer")
    List<Board> findByName(String title, String writer);

    @Query(value = "SELECT * FROM Board", nativeQuery = true)
    List<Board> findAllBoardBySQL();

    @Query(value = "SELECT title, writer FROM Board", nativeQuery = true)
    List<Object[]> findAllBoardBySQL2();

    @Query(value = "SELECT * FROM Board", nativeQuery = true)
    List<Board> findAllBoardBySQL3(Pageable pageable);

    // select count(*) from board where writer = :writer
    int countAllByWriter(String writer);

    // select * from board where writer = :writer
    List<Board> findByWriter(String writer);

    // select * from board where title = :title and writer = :writer
    List<Board> findByTitleAndWriter(String title, String writer);

    // delete from board where writer = :writer
    @Transactional      // delete 경우, 여러 건을 delete할 수 있기 때문에 Tx처리 필수
    int deleteByWriter(String writer);

}
