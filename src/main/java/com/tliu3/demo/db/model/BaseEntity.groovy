package com.tliu3.demo.db.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Version

import org.hibernate.annotations.Type

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import groovy.transform.ToString

@JsonIgnoreProperties(value = ["version"])
@MappedSuperclass
@ToString(includeNames = true, includePackage = false, excludes = [])
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id

	@Version
	Long version

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
	@Column(name = "create_date", updatable = false, nullable = false)
	LocalDateTime createDate;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
	@Column(name = "update_date", updatable = false, nullable = false)
	LocalDateTime updateDate;

	@PrePersist
	@PreUpdate
	private void updateTimestamps() {
		updateDate = LocalDateTime.now()
		if (createDate == null) {
			createDate = LocalDateTime.now()
		}
	}
}
