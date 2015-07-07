package com.tliu3.demo.controllers

import static org.springframework.http.ResponseEntity.*
import static org.springframework.web.bind.annotation.RequestMethod.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.tliu3.demo.service.SubscriptionService

@RestController
@RequestMapping("/api/users/{userId}/subscriptions")
class SubscriptionController {
	private final SubscriptionService subscriptionService

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService
	}

	@RequestMapping(method = GET)
	public ResponseEntity allSubscribedBooks(@PathVariable Long userId) {
		def subscribedBooks = subscriptionService.findAllSubscribedBooks(userId)
		return ok(subscribedBooks)
	}

	@RequestMapping(value = "/{bookId}", method = PUT)
	public ResponseEntity subscribe(@PathVariable Long userId, @PathVariable Long bookId) {
		def subscribedBooks = subscriptionService.subscribe(userId, bookId)
		return ok(subscribedBooks)
	}

	@RequestMapping(value = "/{bookId}", method = DELETE)
	public ResponseEntity unsubscribe(@PathVariable Long userId, @PathVariable Long bookId) {
		def subscribedBooks = subscriptionService.unsubscribe(userId, bookId)
		return ok(subscribedBooks)
	}
}
