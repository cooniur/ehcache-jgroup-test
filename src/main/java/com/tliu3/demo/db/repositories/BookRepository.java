package com.tliu3.demo.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tliu3.demo.db.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findById(Long id);

	Optional<Book> findByName(String name);

	default boolean existsByName(String name) {
		return this.findByName(name).isPresent();
	}
}
