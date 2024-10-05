package com.crio.coderhack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import com.crio.coderhack.exceptions.UserNotFoundException;
import com.crio.coderhack.model.User;
import com.crio.coderhack.repository.UserRepository;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Mock
	private User mockUser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockUser = new User("1", "JohnDoe", 0, null);
		mockUser.setScore(20);
	}

	@Test
	public void testUpdateUserScore_Success() {
		when(userRepository.findById("1")).thenReturn(Optional.of(mockUser));
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
		int newScore = 50;
		User updatedUser = userService.updateUserScore("1", newScore);

		
		assertNotNull(updatedUser);
		assertEquals(50, updatedUser.getScore()); 
		assertEquals("Code Champ", updatedUser.getBadges().iterator().next()); 

		verify(userRepository, times(1)).save(mockUser);
	}

	@Test
    public void testUpdateUserScore_UserNotFound() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUserScore("1", 50);
        });

        verify(userRepository, never()).save(any(User.class));
    }

}
