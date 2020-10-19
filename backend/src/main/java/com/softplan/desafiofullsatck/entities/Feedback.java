package com.softplan.desafiofullsatck.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_feedback")
public class Feedback implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String textFeedback;
	private Instant createdAt;
	 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "process_id")
	private Process process;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_feedback_users",
        joinColumns = @JoinColumn(name = "feedback_id"),
        inverseJoinColumns = @JoinColumn(name = "users_id"))
	private Set<User> users = new HashSet<>();
	
	public Feedback() {
	}

	public Feedback(Long id, String textFeedback, Instant createdAt, Process process) {
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
	
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
