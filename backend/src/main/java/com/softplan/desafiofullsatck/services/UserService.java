package com.softplan.desafiofullsatck.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.desafiofullsatck.dto.UserDto;
import com.softplan.desafiofullsatck.entities.User;
import com.softplan.desafiofullsatck.repositories.UserRepository;
import com.softplan.desafiofullsatck.services.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public Page<UserDto> findAllUsers(PageRequest pageRequest){
		Page<User> users = repository.findAll(pageRequest);
		return users.map(user -> new UserDto(user));
	}
	
	@Transactional(readOnly = true)
	public UserDto findById(Long userId) {
		Optional<User> user = repository.findById(userId);
		User entity = user.orElseThrow(() -> new ResourceNotFoundException("usuario não encontrado na base de dados."));
		return new UserDto(entity);
	}
	
	@Transactional
	public UserDto insert(UserDto userDto) {
		User entity = new User();
		copyDtoToEntity(userDto, entity);
		entity = repository.save(entity);
		return new UserDto(entity);
	}
	
	@Transactional
	public UserDto update(Long userId, UserDto userDto) {
		try {
			User entity = repository.getOne(userId);
			 copyDtoToEntity(userDto, entity);
			 entity = repository.save(entity);
			 return new UserDto(entity);
			}
		     catch(EntityNotFoundException e) {
		    	throw new ResourceNotFoundException("não foi possível atualizar os dados.");
		     }
       }

	public void delete(Long userId) {
		try {
			repository.deleteById(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("não foi possível deletar o usuario.");
	}
		}
	
	private void copyDtoToEntity(UserDto userDto, User entity) {
		entity.setUsername(userDto.getUsername());
		entity.setEmail(userDto.getEmail());
		entity.setPassword(userDto.getPassword());
		entity.setUserRole(userDto.getUserRole());
		
		}
}
