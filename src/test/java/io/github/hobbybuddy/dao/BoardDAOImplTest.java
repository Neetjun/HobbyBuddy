package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class BoardDAOImplTest extends TestCase {

    @Autowired
    BoardDAO dao;

    @Test
    public void testPostBoard() {

        for(int i = 1; i <= 3400; i++)
        {
            BoardDTO dto = new BoardDTO(1,"test"+i,"test"+i);
            dao.postBoard(dto);
        }
    }

    public void testGetBoard() {
    }

    public void testGetWriter() {
    }

    public void testGetBoardList() {
    }

    public void testMyBoardList() {
    }

    public void testIsWriter() {
    }

    public void testDeleteBoard() {
    }

    public void testUpdateBoard() {
    }
}