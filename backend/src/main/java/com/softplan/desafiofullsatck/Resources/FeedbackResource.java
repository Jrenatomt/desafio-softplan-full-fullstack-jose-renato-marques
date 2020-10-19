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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softplan.desafiofullsatck.dto.FeedbackDto;
import com.softplan.desafiofullsatck.services.FeedbackService;

@RestController
@RequestMapping(value = "/feedbacks")
public class FeedbackResource {
	
	@Autowired
	private FeedbackService service;

	@GetMapping
	public ResponseEntity<Page<FeedbackDto>> findAllFeedbacks(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<FeedbackDto> feedback = service.findAllFeedbacks(pageRequest);
		return ResponseEntity.ok().body(feedback);
	}
	
	@GetMapping(value = "/{feedbackId}")
	public ResponseEntity<FeedbackDto> findFeedbackById(@PathVariable Long feedbackId){
		FeedbackDto feedback = service.findFeedbackById(feedbackId);
		return ResponseEntity.ok().body(feedback);	
	}
	
	@PostMapping
	public ResponseEntity<FeedbackDto> CreateFeedback(@RequestBody FeedbackDto feedbackDto){
		feedbackDto = service.addFeedback(feedbackDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{feedbackId}")
				.buildAndExpand(feedbackDto.getId()).toUri();
		return ResponseEntity.created(uri).body(feedbackDto);
	}
}
