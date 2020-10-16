package com.softplan.desafiofullsatck.dto;

import java.io.Serializable;

import com.softplan.desafiofullsatck.entities.User;
import com.softplan.desafiofullsatck.entities.enums.Role;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private Role userRole;

	public UserDto() {
	}
	
	public UserDto(User entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
		this.userRole = entity.getUserRole();
		
	}

	public UserDto(Long id, String username, String email, String password, Role userRole) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
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
		UserDto other = (UserDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
