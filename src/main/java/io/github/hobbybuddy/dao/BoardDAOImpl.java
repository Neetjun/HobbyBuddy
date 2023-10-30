package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession session;
    private String namespace = "board.";
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
    public List<BoardDTO> getBoardList(HashMap<String, String> map)
    {
        return session.selectList(namespace+"getBoardList",map);
    }
    @Override
    public List<BoardDTO> myBoardList(Integer uno)
    {
        return session.selectList(namespace+"myBoardList",uno);
    }
    @Override
    public String isWriter(BoardDTO dto) { return session.selectOne(namespace+"isWriter",dto); }
    @Override
    public void deleteBoard(BoardDTO dto) {session.delete(namespace+"deleteBoard",dto);}
    @Override
    public void updateBoard(BoardDTO dto) {session.update(namespace+"updateBoard",dto);}
    @Override
    public int likeCnt(HashMap<String,String> likeMap) {return session.selectOne(namespace+"likeCnt",likeMap);}
    @Override
    public void likeContent(HashMap<String,String> likeMap) {session.insert(namespace+"likeContent",likeMap);}
    @Override
    public void updateLikeCnt(HashMap<String,String> likeMap) {session.update(namespace+"updateLikeCnt",likeMap);}
}
