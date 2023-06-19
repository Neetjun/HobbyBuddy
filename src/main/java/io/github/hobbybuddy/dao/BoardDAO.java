package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;

import java.util.List;

public interface BoardDAO {
    void postBoard(BoardDTO dto);
    BoardDTO getBoard(Integer bno);
    String getWriter(Integer bno);
    List<BoardDTO> getBoardList();
    String isWriter(BoardDTO dto);
}
