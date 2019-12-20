package com.yu;

import com.yu.entity.User;
import com.yu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhcacheApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void findByName() {
        userService.findByName("admin");
    }

    @Test
    public void update() {
        User user = new User();
        user.setUserId("2");
        user.setUsername("aaaa");
        userService.update(user);
    }

    @Test
    public void delete() {
        userService.delete("aaaa");
    }


}
