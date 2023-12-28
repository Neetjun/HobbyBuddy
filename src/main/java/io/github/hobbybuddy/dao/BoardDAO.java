package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardDAO {
    void postBoard(BoardDTO dto);
    BoardDTO getBoard(Integer bno);
    String getWriter(Integer bno);
    List<BoardDTO> getBoardList(HashMap<String, String> map);
    String isWriter(BoardDTO dto);
    void deleteBoard(BoardDTO dto);
    void updateBoard(BoardDTO dto);
    List<BoardDTO> myBoardList(Integer uno);
    int likeCnt(HashMap<String,String> likeMap);
    void likeContent(HashMap<String,String> likeMap);
    void updateLikeCnt(HashMap<String,String> likeMap);
    void updateViewCnt(BoardDTO dto);
    void insertImg(HashMap<String,Object> fileMap);
    List<String> getImg(Integer bno);
}
