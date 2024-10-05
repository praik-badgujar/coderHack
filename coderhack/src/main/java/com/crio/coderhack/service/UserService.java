package com.crio.coderhack.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.coderhack.exceptions.UserNotFoundException;
import com.crio.coderhack.model.User;
import com.crio.coderhack.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll().stream().sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
				.toList();
	}

	public Optional<User> getUserById(String userId) {
		return userRepository.findById(userId);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUserScore(String userId, int score) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setScore(score);
			updateBadges(user);
			return userRepository.save(user);
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	private void updateBadges(User user) {
		Set<String> badges = new TreeSet<>();
		int score = user.getScore();
		if (score >= 1 && score < 30) {
			badges.add("Code Ninja");
		} else if (score >= 30 && score < 60) {
			badges.add("Code Champ");
		} else if (score >= 60 && score <= 100) {
			badges.add("Code Master");
		}
		user.setBadges(badges);
	}

	public void deleteUser(String userId) {
		userRepository.deleteById(userId);

	}

}
