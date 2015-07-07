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

import com.tliu3.demo.service.UserService

@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
class UserController {
	private final UserService userService

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService
	}

	@RequestMapping(method = GET)
	public ResponseEntity allUsers() {
		def all = userService.findAll()
		return ok(all)
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity create(@RequestBody Map userDTO) {
		def u = userService.create(userDTO)
		return ok(u)
	}

	@RequestMapping(value = "/{userId}", method = GET)
	public ResponseEntity findOne(@PathVariable Long userId) {
		def u = userService.findOne(userId)
		return ok(u)
	}

	@RequestMapping(value = "/{userId}", method = PUT, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable Long userId,
	                             @RequestBody Map userDTO) {
		def user = userService.update(userId, userDTO)
		return ok(user)
	}
}
