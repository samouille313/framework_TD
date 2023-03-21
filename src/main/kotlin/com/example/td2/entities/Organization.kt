package com.example.td2.entities

import jakarta.persistence.*

@Entity
open class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=null

    @Column(nullable = false, unique = true, length = 45)
    open lateinit var name:String

    @Column(length = 45)
    open var domain:String?=null

    @Column(length = 30)
    open var aliases:String?=null

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    open val users= mutableSetOf<User>()

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    open val groups= mutableSetOf<Groupe>()

    fun addUser(user: User):Boolean {
        if(users.add(user)){
            user.organization=this
            return true
        }
        return false
    }
}