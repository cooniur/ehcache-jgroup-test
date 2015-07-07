package com.tliu3.demo.db.model

import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import groovy.transform.ToString

@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@ToString(includePackage = false, includeNames = true, includeSuper = true, excludes = [])
public class Book extends BaseEntity {
	String name
}
