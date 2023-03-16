package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Role() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int=0

    @Column(length = 65, nullable = false)
    open lateinit var name:String

   /* @OneToMany
    @Column(length = 256)
    open lateinit var users:List<User>*/
}