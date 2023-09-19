package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.CommentDTO;

import java.util.List;

public interface CommentDAO {
    void postComment(CommentDTO commentDTO);
    void deleteComment(CommentDTO commentDTO);
    List<CommentDTO> getComment(Integer bno);
}
