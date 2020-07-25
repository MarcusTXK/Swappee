package com.swappee.service.user;

import com.swappee.model.user.UserDTO;
import com.swappee.service.item.ItemServiceImplTest;
import com.swappee.utils.exception.BaseServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserService userService;

    @Test
    public void findUserById() {
    }

    @Test
    public void findUserViewById() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void create() throws BaseServiceException {
        UserDTO userDTO = userService.create(createUser());
        logger.info("Created UserDTO: {}", userDTO);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    private UserDTO createUser() {
        UserDTO userDTO= new UserDTO();
        userDTO.setFirstName("Biscuit");
        userDTO.setLastName("Milo");
        userDTO.setUsername("Biscuit2015");
        userDTO.setPassword("test");
        userDTO.setEmail("Dog@email.com");
        userDTO.setPhone(123456789L);
        userDTO.setEmailOnly(true);
        userDTO.setRole("USER");
        userDTO.setScore(500L);
        userDTO.setTotalTraded(100L);
        return userDTO;
    }
}