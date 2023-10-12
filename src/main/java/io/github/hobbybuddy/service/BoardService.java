package io.github.hobbybuddy.service;

import io.github.hobbybuddy.dao.BoardDAO;
import io.github.hobbybuddy.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService
{
    @Autowired
    private BoardDAO dao;

    public void postBoard(BoardDTO dto)
    {
        dao.postBoard(dto);
    }
    public BoardDTO getBoard(Integer bno)
    {
        return dao.getBoard(bno);
    }
    public String getWriter(Integer bno)
    {
        return dao.getWriter(bno);
    }
    public List<BoardDTO> getBoardList(HashMap<String,String> map)
    {
        return dao.getBoardList(map);
    }
    public List<BoardDTO> myBoardList(Integer uno)
    {
        return dao.myBoardList(uno);
    }
    public String isWriter(BoardDTO dto)
    {
        return dao.isWriter(dto);
    }
    public void deleteBoard(BoardDTO dto) { dao.deleteBoard(dto); }
    public void updateBoard(BoardDTO dto) { dao.updateBoard(dto); }
}
