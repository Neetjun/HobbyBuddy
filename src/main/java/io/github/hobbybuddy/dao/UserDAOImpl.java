package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SqlSession session;
    String namespace = "user.";

    // 회원가입
    @Override
    public int registration(UserDTO user)
    {
        return session.insert(namespace+"registration", user);
    }
    @Override
    public UserDTO login(UserDTO user)
    {
        return session.selectOne(namespace+"login", user);
    }
    @Override
    public String dupCheck(String id) {return session.selectOne(namespace+"dupCheck", id); }
    @Override
    public void modNickname(UserDTO user) {session.update(namespace+"updateNickname",user); }
}
