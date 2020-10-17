package com.softplan.desafiofullsatck.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.softplan.desafiofullsatck.entities.Feedback;
import com.softplan.desafiofullsatck.entities.Process;
import com.softplan.desafiofullsatck.entities.User;

public class FeedbackDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String textFeedback;
	private Instant createdAt;
	
	private Process process;
	
	private Set<UserDto> users = new HashSet<>();
	
	public FeedbackDto() {
	}
	
	public FeedbackDto(Feedback entity) {
		this.id = entity.getId();
		this.textFeedback = entity.getTextFeedback();
		this.createdAt = entity.getCreatedAt();
		this.process = entity.getProcess();
	}
	
	public FeedbackDto(Feedback entity, Set<User> users) {
		this(entity);
		users.forEach( user -> this.users.add(new UserDto(user)));
	}

	public FeedbackDto(Long id, String textFeedback, Instant createdAt, Process process) {
		this.id = id;
		this.textFeedback = textFeedback;
		this.createdAt = createdAt;
		this.process = process;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextFeedback() {
		return textFeedback;
	}

	public void setTextFeedback(String textFeedback) {
		this.textFeedback = textFeedback;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}
}
