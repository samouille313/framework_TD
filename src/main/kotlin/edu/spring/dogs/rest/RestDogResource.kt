package edu.spring.dogs.rest

import edu.spring.dogs.entities.Dog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

@RepositoryRestResource(collectionResourceRel = "dogs", path = "dogs")
interface RestDogResource : JpaRepository<Dog, Int> {

    @RestResource(exported = true)
    fun findByMasterIsNull():List<Dog>
}