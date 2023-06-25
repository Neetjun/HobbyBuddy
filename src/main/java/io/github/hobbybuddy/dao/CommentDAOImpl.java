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
    public List<CommentDTO> getComment(Integer bno) { return session.selectList(namespace+"getCmt",bno); }

}
