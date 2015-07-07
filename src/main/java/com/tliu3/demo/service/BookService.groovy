package com.tliu3.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.tliu3.demo.db.model.Book
import com.tliu3.demo.db.repositories.BookRepository
import com.tliu3.demo.exceptions.BookNameConflictException
import com.tliu3.demo.exceptions.BookNotFoundException

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
		return bookRepo.findById(id).orElseThrow({ new BookNotFoundException(id) })
	}

	@Transactional
	public Book update(Long id, Map bookDTO) {
		checkIfNameExists(bookDTO.name)
		def book = bookRepo.findById(id).orElseThrow({ new BookNotFoundException(id) })
		book.name = bookDTO.name
		book = bookRepo.save(book)
		return book
	}

	private void checkIfNameExists(String name) {
		if (bookRepo.exists(name)) {
			throw new BookNameConflictException(name)
		}
	}
}
