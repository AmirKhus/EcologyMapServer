package com.example.vkr_server;//package com.example.coreinfrastructurevk;
//
//import com.example.coreinfrastructurevk.controller.UserController;
//import com.example.coreinfrastructurevk.model.User;
//import com.example.coreinfrastructurevk.repository.MarkerRepository;
//import com.example.coreinfrastructurevk.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.junit.Assert;
//import org.springframework.util.Assert;
//
//@Controller
//@SpringBootTest
//public class UserTestController {
//    @MockBean
//    private UserController userController;
//    @MockBean
//    private MarkerRepository userRepository;
//
//    Class<ResponseEntity> UserController = null;
//
//
//    @Test
//    public void loginUser() {
//        User user = new User();
//        user.setEmail("testEmail");
//        user.setUsername("testUser");
//        user.setPassword("password");
//        ResponseEntity<User> isUserCreate = userRepository.;
//
//        Assert.isInstanceOf(UserController,isUserCreate);
//        Mockito.verify(userController, Mockito.times(1)).createUser(user);
//    }
//
//}