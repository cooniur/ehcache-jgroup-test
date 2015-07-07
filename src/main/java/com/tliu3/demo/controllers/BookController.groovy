package com.tliu3.demo.controllers

import static org.springframework.http.MediaType.*
import static org.springframework.http.ResponseEntity.*
import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.tliu3.demo.service.BookService

@RestController
@RequestMapping(value = "/api/books", produces = APPLICATION_JSON_VALUE)
class BookController {
	private final BookService bookService

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService
	}

	@RequestMapping(method = POST)
	public ResponseEntity create(@RequestBody Map bookDTO) {
		def book = bookService.create(bookDTO)
		return ok(book)
	}

	@RequestMapping(method = GET)
	public ResponseEntity allBooks() {
		def all = bookService.findAll()
		return ok(all)
	}

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity findOne(@PathVariable Long id) {
		def book = bookService.findOne(id)
		return ok(book)
	}

	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity update(@PathVariable Long id, @RequestBody Map bookDTO) {
		def book = bookService.update(id, bookDTO)
		return ok(book)
	}
}
