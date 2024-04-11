package com.sptek.demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getListTest(){
        List<Board> list = boardService.getList();
        System.out.println("list = " + list);
        assertEquals(list.size(), 10);
    }

    @BeforeEach
    public void init(){
        for(Long i = 1L; i<=10; i++){
            Board b = new Board();
            b.setBno(i);
            b.setTitle("title"+i);
            b.setContent("content"+i);
            User user = new User();
            user.setId("aaa");
            userRepository.save(user);

            b.setUser(user);
            b.setViewCnt(0);
            b.setInDate(new Date());
            b.setUpDate(new Date());
            boardService.write(b);
        }
    }

    @Test
    public void writeAndReadTest(){
        User user = new User();
        user.setId("bbb");
        userRepository.save(user);

        Board b = new Board();
        b.setBno(11L);
        b.setTitle("new Title");
        b.setContent("new Content");
        b.setUser(user);
        b.setViewCnt(0);
        b.setInDate(new Date());
        b.setUpDate(new Date());
        boardService.write(b);

        Board b2 = boardService.read(11L);
        assertTrue(b2 != null);
        assertEquals(b.getTitle(), b2.getTitle());
        assertEquals(b.getContent(), b2.getContent());
    }

    @Test
    public void modifyTest(){
        Board board = boardService.read(1L);
        board.setTitle("modifed title");
        board.setContent("modifed content");
        boardService.modify(board);

        Board board2 = boardService.read(1L);
        assertEquals(board.getTitle(), board2.getTitle());
        assertEquals(board.getContent(), board2.getContent());
    }

    @Test
    public void removeTest(){
        Long bno = 5L;
//        Board board = boardService.read(bno);
//        if(board != null){
//            boardService.remove(bno);
//        }
        // 위에 주석 처리된 내용을 간단하게 아래 코드로 쓸 수 있음

        assertTrue(boardService.read(bno)!=null);
        boardService.remove(bno);
        assertEquals(boardService.read(bno), null);


    }

}