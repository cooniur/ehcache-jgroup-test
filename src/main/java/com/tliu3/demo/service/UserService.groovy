package com.tliu3.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.tliu3.demo.db.model.User
import com.tliu3.demo.db.repositories.UserRepository
import com.tliu3.demo.exceptions.ConflictException
import com.tliu3.demo.exceptions.NotFoundException

@Service
public class UserService {
	private final UserRepository userRepo

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepo.findAll()
	}

	@Transactional
	public User create(Map userDTO) {
		String name = userDTO.name
		if (userRepo.existsByName(name)) {
			throw new ConflictException("User [$name] already exists.")
		}

		def u = new User(name: userDTO.name,
				sex: userDTO.sex,
				age: userDTO.age)
		return userRepo.save(u)
	}

	@Transactional(readOnly = true)
	public User findOne(Long userId) {
		return userRepo.findById(userId).orElseThrow({ new NotFoundException("User [$userId] not found.") })
	}

	@Transactional
	public User update(Long userId, Map userDTO) {
		def user = userRepo.findById(userId).orElseThrow({ new NotFoundException("User [$userId] not found.") })
		user.sex = userDTO.sex
		user.age = userDTO.age
		return userRepo.save(user)
	}
}
