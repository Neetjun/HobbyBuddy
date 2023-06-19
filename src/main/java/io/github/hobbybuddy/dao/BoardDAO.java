package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.BoardDTO;

import java.util.List;

public interface BoardDAO {
    void postBoard(BoardDTO dto);
    BoardDTO getBoard(String bno);
    String getWriter(String bno);
    List<BoardDTO> getBoardList();
}
