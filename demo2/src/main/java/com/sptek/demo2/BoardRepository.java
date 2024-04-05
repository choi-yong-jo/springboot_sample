package com.sptek.demo2;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

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
