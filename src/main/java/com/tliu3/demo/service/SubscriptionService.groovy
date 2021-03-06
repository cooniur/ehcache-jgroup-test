package com.tliu3.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.tliu3.demo.db.model.Book
import com.tliu3.demo.db.repositories.BookRepository
import com.tliu3.demo.db.repositories.UserRepository
import com.tliu3.demo.exceptions.BookNotFoundException
import com.tliu3.demo.exceptions.UserNotFoundException

@Service
public class SubscriptionService {
	private final UserRepository userRepo
	private final BookRepository bookRepo

	@Autowired
	public SubscriptionService(UserRepository userRepo, BookRepository bookRepo) {
		this.userRepo = userRepo
		this.bookRepo = bookRepo
	}

	@Transactional
	public Set<Book> subscribe(Long userId, Long bookId) {
		def book = bookRepo.findById(bookId).orElseThrow({ new BookNotFoundException(bookId) })
		def user = userRepo.findById(userId).orElseThrow({ new UserNotFoundException(userId) })
		user.books.add(book)
		user = userRepo.save(user)
		return user.books
	}

	@Transactional
	public Set<Book> unsubscribe(Long userId, Long bookId) {
		def book = bookRepo.findById(bookId).orElseThrow({ new BookNotFoundException(bookId) })
		def user = userRepo.findById(userId).orElseThrow({ new UserNotFoundException(userId) })
		user.books.remove(book)
		user = userRepo.save(user)
		return user.books
	}

	@Transactional(readOnly = true)
	public Set<Book> findAllSubscribedBooks(Long userId) {
		def user = userRepo.findById(userId).orElseThrow({ new UserNotFoundException(userId) })
		return user.books
	}
}
