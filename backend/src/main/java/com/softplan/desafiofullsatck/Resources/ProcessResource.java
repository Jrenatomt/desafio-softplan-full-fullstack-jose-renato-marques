package com.softplan.desafiofullsatck.Resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softplan.desafiofullsatck.dto.ProcessDto;
import com.softplan.desafiofullsatck.services.ProcessService;

@RestController
@RequestMapping(value = "/processes")
public class ProcessResource {

	@Autowired
	private ProcessService service;

	@GetMapping
	public ResponseEntity<Page<ProcessDto>> findAllProcesses(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		PageRequest pegaRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProcessDto> process = service.findAllProcesses(pegaRequest);
		return ResponseEntity.ok().body(process);
	}
	
	@GetMapping(value = "/{processId}")
	public ResponseEntity<ProcessDto> findProcessById(@PathVariable Long processId){
		ProcessDto ProcessDto = service.findProcessById(processId);
		return ResponseEntity.ok().body(ProcessDto);	
	}
	
	@PostMapping
	public ResponseEntity<ProcessDto> CreateProcess(@RequestBody ProcessDto ProcessDto){
		ProcessDto = service.insert(ProcessDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{processId}")
				.buildAndExpand(ProcessDto.getId()).toUri();
		return ResponseEntity.created(uri).body(ProcessDto);
	}
	
	@PutMapping(value = "/{processId}")
	public ResponseEntity<ProcessDto> updateProcess(@PathVariable Long processId,@RequestBody ProcessDto dto){
		dto = service.update(processId, dto);
		return ResponseEntity.ok().body(dto);
	}	
	

}