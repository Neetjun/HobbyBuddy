package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.CommentDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private SqlSession session;
    private String namespace = "comment.";

    @Override
    public void postComment(CommentDTO commentDTO)
    {
        session.insert(namespace+"postCmt",commentDTO);
    }
    @Override
    public void deleteComment(CommentDTO commentDTO)
    {
        session.delete(namespace+"deleteCmt",commentDTO);
    }
    @Override
    public List<CommentDTO> getComment(Integer bno) { return session.selectList(namespace+"getCmt",bno); }
    @Override
    public void postReply(CommentDTO commentDTO) { session.insert(namespace+"postReply",commentDTO);}


}
