package com.tliu3.demo.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tliu3.demo.db.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);

	Optional<User> findByName(String name);

	default boolean exists(String name) {
		return this.findByName(name).isPresent();
	}
}
