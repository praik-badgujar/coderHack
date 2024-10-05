package com.crio.coderhack.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Generated;


@Document(collection = "users")
public class User {

	@Id()
	private String id;
    private String username;
    private int score;
    private Set<String> badges;
    
    public User() {
    	
    }
    
	public User(String id, String username, int score, Set<String> badges) {
		super();
		this.id = id;
		this.username = username;
		this.score = score;
		this.badges = badges;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Set<String> getBadges() {
		return badges;
	}
	public void setBadges(Set<String> badges) {
		this.badges = badges;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", score=" + score + ", badges=" + badges + "]";
	}
    
	
    
}
