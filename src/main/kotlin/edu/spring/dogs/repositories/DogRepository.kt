package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository:CrudRepository<Dog, Int> {
    fun findByMasterIsNull(): List<Dog>

    fun findByNameAndMasterId(s: String, id: Int?): Dog

}