package io.github.hobbybuddy.service;

import io.github.hobbybuddy.dao.UserDAO;
import io.github.hobbybuddy.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public int registUser(UserDTO user)
    {
        return userDAO.registration(user);
    }

    public UserDTO loginUser(UserDTO user)
    {
        return userDAO.login(user);
    }

}
