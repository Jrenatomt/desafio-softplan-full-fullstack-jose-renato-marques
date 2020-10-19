package com.softplan.desafiofullsatck.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.desafiofullsatck.dto.ProcessDto;
import com.softplan.desafiofullsatck.dto.UserDto;
import com.softplan.desafiofullsatck.entities.Process;
import com.softplan.desafiofullsatck.entities.User;
import com.softplan.desafiofullsatck.repositories.ProcessRepository;
import com.softplan.desafiofullsatck.repositories.UserRepository;
import com.softplan.desafiofullsatck.services.exception.ResourceNotFoundException;

@Service
public class ProcessService {
	
	@Autowired
	private ProcessRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<ProcessDto> findAllProcesses(PageRequest pageRequest){
		Page<Process> entity = repository.findAll(pageRequest);
		return entity.map(obj -> new ProcessDto(obj));
	}
	
	@Transactional(readOnly = true)
	public ProcessDto findProcessById(Long processId) {
		Optional<Process> process = repository.findById(processId);
		Process entity = process.orElseThrow(() -> new ResourceNotFoundException("processo não encontrado na base de dados."));
		return new ProcessDto(entity, entity.getUsers());
	}
	
	@Transactional
	public ProcessDto insert(ProcessDto processDto) {
		Process entity = new Process();
		copyDtoToEntity(processDto, entity);
		entity = repository.save(entity);
		return new ProcessDto(entity);
	}
	
	@Transactional
	public ProcessDto update(Long id, ProcessDto ProcessDto) {
		try {
			 Process entity = repository.getOne(id);
			 copyDtoToEntity(ProcessDto, entity);
			 entity = repository.save(entity);
			 return new ProcessDto(entity);
			}
		     catch(EntityNotFoundException e) {
		    	throw new ResourceNotFoundException("Processo não encontrado para o id" + id);
		     }
       }	
	
	private void copyDtoToEntity(ProcessDto processDto, Process entity) {
		entity.setName(processDto.getName());
		entity.setDescription(processDto.getDescription());
		entity.setProcessStatus(processDto.getProcessStatus());
		
		for (UserDto userDto : processDto.getUsers()) {
			User user = userRepository.getOne(userDto.getId());
			entity.getUsers().add(user);
		}
	}
}
