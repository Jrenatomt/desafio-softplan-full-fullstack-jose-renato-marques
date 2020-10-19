package com.softplan.desafiofullsatck.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softplan.desafiofullsatck.entities.User;
import com.softplan.desafiofullsatck.entities.enums.Role;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 2, max = 120, message="O tamanho deve ser entre 2 e 120 caracteres")
	private String firstName;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 2, max = 120, message="O tamanho deve ser entre 2 e 120 caracteres")
	private String lastName;
	
	@Email
	private String email;
	
	@JsonIgnore
	private Role userRole;

	public UserDto() {
	}
	
	public UserDto(User entity) {
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.email = entity.getEmail();
		this.userRole = entity.getUserRole();
		
	}

	public UserDto(Long id, String username, String email, String password, Role userRole) {
		this.id = id;
		this.firstName = username;
		this.lastName = username;
		this.email = email;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
