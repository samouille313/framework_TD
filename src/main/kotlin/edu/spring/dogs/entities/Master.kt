package edu.spring.dogs.entities

import edu.spring.dogs.DogsApplication
import jakarta.persistence.*

@Entity
open class Master() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @Column(nullable = false, unique = false, length = 45)
    open lateinit var firstname: String

    @Column(nullable = false, unique = false, length = 45)
    open lateinit var lastname:String

    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = [CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH])
    open val dogs = mutableSetOf<Dog>()

    constructor(firstname:String, lastname:String):this(){
        this.firstname=firstname
        this.lastname=lastname
    }
    fun addDog( dog: Dog):Boolean{
        if(dogs.add(dog)){
            dog.master=this
            return true
        }
        return false
    }
    fun giveUpDog(dog: Dog):Boolean{
        if(dogs.remove(dog)){
            dog.master=null
            return true
        }
        return false
    }
    @PreRemove
    fun preRemove():Unit{
        dogs.forEach(){
            it.master = null
        }

    }

}