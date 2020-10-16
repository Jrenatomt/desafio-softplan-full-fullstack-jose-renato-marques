package com.softplan.desafiofullsatck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softplan.desafiofullsatck.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
