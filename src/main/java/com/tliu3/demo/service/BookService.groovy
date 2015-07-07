package com.tliu3.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.tliu3.demo.db.model.Book
import com.tliu3.demo.db.repositories.BookRepository
import com.tliu3.demo.exceptions.ConflictException
import com.tliu3.demo.exceptions.NotFoundException

@Service
public class BookService {
	private final BookRepository bookRepo

	@Autowired
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo
	}

	@Transactional
	public Book create(Map bookDTO) {
		checkIfNameExists(bookDTO.name)
		def book = new Book(name: bookDTO.name)
		return bookRepo.save(book)
	}

	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return bookRepo.findAll()
	}

	@Transactional(readOnly = true)
	public Book findOne(Long id) {
		return bookRepo.findById(id).orElseThrow({ new NotFoundException("Book [$id] not found.") })
	}

	@Transactional
	public Book update(Long id, Map bookDTO) {
		checkIfNameExists(bookDTO.name)
		def book = this.findOne(id)
		book.name = bookDTO.name
		book = bookRepo.save(book)
		return book
	}

	private void checkIfNameExists(String name) {
		if (bookRepo.existsByName(name)) {
			throw new ConflictException("Book [$name] already exists.")
		}
	}
}
