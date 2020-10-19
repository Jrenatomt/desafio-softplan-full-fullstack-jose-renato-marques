package com.softplan.desafiofullsatck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.desafiofullsatck.dto.FeedbackDto;
import com.softplan.desafiofullsatck.entities.Feedback;
import com.softplan.desafiofullsatck.repositories.FeedbackRepository;
import com.softplan.desafiofullsatck.services.exception.ResourceNotFoundException;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository repository;
	
	@Transactional(readOnly = true)
	public Page<FeedbackDto> findAllFeedbacks(PageRequest pageRequest){
		Page<Feedback> entity = repository.findAll(pageRequest);
		return entity.map(obj -> new FeedbackDto(obj));
	}
	
	@Transactional(readOnly = true)
	public FeedbackDto findFeedbackById(Long feedbackId) {
		Optional<Feedback> feedback = repository.findById(feedbackId);
		Feedback entity = feedback.orElseThrow(() -> new ResourceNotFoundException("Parecer não encontrado na base de dados."));
		return new FeedbackDto(entity, entity.getUsers());
	}
	
	@Transactional
	public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
		Feedback entity = new Feedback();
		entity.setProcess(feedbackDto.getProcess());
		entity.setTextFeedback(feedbackDto.getTextFeedback());
		repository.save(entity);
		return new FeedbackDto(entity);
	}
}
