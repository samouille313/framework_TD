package edu.spring.dogs.entities;

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne

@Entity
open class Dog() {
	constructor(name:String):this(){
		this.name=name
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	open var id = 0

	@Column(length = 30, nullable = false)
	open lateinit var name: String

	@ManyToOne(optional = true)
	@JsonBackReference
	open var master: Master?=null

	@ManyToMany
	open val toys= mutableSetOf<Toy>()
}
