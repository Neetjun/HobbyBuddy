package io.github.hobbybuddy.service;

import io.github.hobbybuddy.dao.BoardDAO;
import io.github.hobbybuddy.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDAO dao;

    public void postBoard(BoardDTO dto)
    {
        dao.postBoard(dto);
    }
    public BoardDTO getBoard(String bno)
    {
        return dao.getBoard(bno);
    }

    public String getWriter(String bno)
    {
        return dao.getWriter(bno);
    }

    public List<BoardDTO> getBoardList()
    {
        return dao.getBoardList();
    }
}
