package com.softplan.desafiofullsatck.Resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softplan.desafiofullsatck.dto.UserDto;
import com.softplan.desafiofullsatck.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<Page<UserDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "firstName") String orderBy) {

		PageRequest pegaRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<UserDto> users = service.findAllUsers(pegaRequest);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDto> findUserById(@PathVariable Long userId){
		UserDto userDto = service.findById(userId);
		return ResponseEntity.ok().body(userDto);	
	}
	
	@PostMapping
	public ResponseEntity<UserDto> InsertUser(@RequestBody UserDto userDto){
		userDto = service.insert(userDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(uri).body(userDto);
	}	
	
	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,@RequestBody UserDto userDto){
		userDto = service.update(userId, userDto);
		return ResponseEntity.ok().body(userDto);
	}	
	
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId){
		service.delete(userId);
		return ResponseEntity.noContent().build();
	}
}