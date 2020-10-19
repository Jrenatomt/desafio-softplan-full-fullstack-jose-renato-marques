package com.softplan.desafiofullsatck.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.softplan.desafiofullsatck.entities.Process;
import com.softplan.desafiofullsatck.entities.User;
import com.softplan.desafiofullsatck.entities.enums.ProcessStatus;

public class ProcessDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String name;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private Instant createdAt;
	
	private ProcessStatus processStatus;

	private Set<UserDto> users = new HashSet<>();
	
	public ProcessDto() {
	}

	public ProcessDto(Long id, String name, String description, ProcessStatus processStatus, Instant createdAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.processStatus = processStatus;
		this.createdAt = createdAt;
	}
	
	public ProcessDto(Process entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.processStatus = entity.getProcessStatus();
		this.createdAt = entity.getCreatedAt();
	}
	
	public ProcessDto(Process entity, Set<User> users) {
		this(entity);
		users.forEach( user -> this.users.add(new UserDto(user)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
}
