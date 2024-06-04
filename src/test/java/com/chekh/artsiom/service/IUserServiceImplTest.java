package com.chekh.artsiom.service;

import com.chekh.artsiom.model.User;
import com.chekh.artsiom.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private IUserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("password");

        String encodedPassword = "XeAr1v1";

        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);

        Long saveUserId = userService.saveUser(user);

        assertEquals(user.getId(), saveUserId);

        verify(userRepository).save(user);

        verify(passwordEncoder).encode("password");
    }
}
