package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    SqlSession session;
    String namespace = "board.";
    @Override
    public void postBoard(BoardDTO dto)
    {
        session.insert(namespace+"postBoard",dto);
    }
    @Override
    public BoardDTO getBoard(Integer bno)
    {
        return session.selectOne(namespace+"getBoard",bno);
    }
    @Override
    public String getWriter(Integer bno)
    {
        return session.selectOne(namespace+"getWriter",bno);
    }
    @Override
    public List<BoardDTO> getBoardList()
    {
        return session.selectList(namespace+"getBoardList");
    }
    @Override
    public String isWriter(BoardDTO dto)
    {
        return session.selectOne(namespace+"isWriter",dto);
    }

}
