package com.crio.coderhack.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.crio.coderhack.model.User;
import com.crio.coderhack.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void testUpdateUserScore_Success() throws Exception {
		User mockUser = new User("1", "JohnDoe", 0, null);
		mockUser.setScore(50);
		Mockito.when(userService.updateUserScore(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockUser);

		mockMvc.perform(put("/users/1").contentType("application/json").content("{\"score\": 50}"))
				.andExpect(status().isOk());
	}
}
