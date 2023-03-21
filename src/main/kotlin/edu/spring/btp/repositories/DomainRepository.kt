package edu.spring.btp.repositories

import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DomainRepository:JpaRepository<Domain, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" ORDER BY rand() LIMIT 1")
    fun getRandomDomain(): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" INNER JOIN \"domain_providers\" ON \"providers_id\"=:providerId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromProvider(providerId:Int): Domain

    //fun findByParentName(name:String):List<Domain>

    fun findByName(name: String): Domain?

    // Recherche par name du parent (plusieurs)
    fun findByParentName(name: String): List<Domain>

    // Recherche des domaines n’ayant pas de parent (plusieurs)
    fun findByParentIsNull(): List<Domain>

    // Recherche des domaines par id du parent (plusieurs)
    fun findByParentId(parentId: Int): List<Domain>

    // Retourne un domaine aléatoire à partir d’un id de parent (1 seul)
    @Query(nativeQuery = true, value = "SELECT * FROM domain WHERE parent_id = :parentId ORDER BY RANDOM() LIMIT 1")
    fun getRandomDomainFromParentId(parentId: Int): Domain?

}