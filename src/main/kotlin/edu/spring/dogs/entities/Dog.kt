package edu.spring.dogs.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
@Entity
open class Dog() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @Column(length = 45)
    open lateinit var name:String

    @OneToMany
    open val toys = mutableSetOf<Toy>()

    @ManyToOne
    open var master:Master?=null

    constructor(name:String):this(){
        this.name=name
    }
}