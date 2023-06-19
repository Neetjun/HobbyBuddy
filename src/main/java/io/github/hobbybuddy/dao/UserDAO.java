package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.UserDTO;

public interface UserDAO {
    // 회원가입
    int registration(UserDTO user);
    UserDTO login(UserDTO user);
    String dupCheck(String id);
    void modNickname(UserDTO user);
}
