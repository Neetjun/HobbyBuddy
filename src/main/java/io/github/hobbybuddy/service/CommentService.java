package io.github.hobbybuddy.service;

import io.github.hobbybuddy.dao.CommentDAO;
import io.github.hobbybuddy.domain.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService
{
    @Autowired
    private CommentDAO commentDAO;

    public void postComment(CommentDTO commentDTO)
    {
        commentDAO.postComment(commentDTO);
    }
    public void deleteComment(CommentDTO commentDTO) { commentDAO.deleteComment(commentDTO);}
    public List<CommentDTO> getComment(Integer bno) { return commentDAO.getComment(bno); }
}
