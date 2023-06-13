package io.github.hobbybuddy.dao;

import io.github.hobbybuddy.domain.UserDTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class UserDAOImplTest extends TestCase {

    @Autowired
    UserDAO userDao;

    @Test
    public void testRegistration()
    {
        UserDTO user = new UserDTO(1,"thisistest","asdfzxcv12","닉네임",new Date());

        int result = userDao.registration(user);

        assertEquals(1,result);

    }
}