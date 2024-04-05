package com.sptek.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest(){
        Board board = new Board();
        board.setBno(1);
        board.setTitle("Test Title");
        board.setContent("This is Test");
        board.setWriter("aaa");
        board.setViewCnt(0);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepository.save(board);
    }

}