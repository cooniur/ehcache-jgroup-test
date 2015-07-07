package com.tliu3.demo.db.model

import javax.persistence.Cacheable
import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

import groovy.transform.ToString

@Entity
@Table(name = "book")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@ToString(includePackage = false, includeNames = true, includeSuper = true, excludes = [])
public class Book extends BaseEntity {
	String name
}
