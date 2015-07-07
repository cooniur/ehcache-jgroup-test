package com.tliu3.demo.db.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

import com.fasterxml.jackson.annotation.JsonIgnore

import groovy.transform.ToString

@Entity
@Table(name = "user")
@ToString(includePackage = false, includeNames = true, includeSuper = true)
@Cache(region = "common", usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class User extends BaseEntity {
	String name

	String sex

	Long age

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_book_subscription", joinColumns = [@JoinColumn(name = "user_id", referencedColumnName = "id")],
			inverseJoinColumns = [@JoinColumn(name = "book_id", referencedColumnName = "id")])
	@Fetch(FetchMode.SELECT)
	Set<Book> books = new HashSet<>()
}
