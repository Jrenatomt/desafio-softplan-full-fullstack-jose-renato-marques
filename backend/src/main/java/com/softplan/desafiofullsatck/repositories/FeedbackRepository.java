package com.softplan.desafiofullsatck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softplan.desafiofullsatck.entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
}
