package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;

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
}
