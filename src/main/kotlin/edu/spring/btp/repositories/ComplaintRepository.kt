package edu.spring.btp.repositories

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository

interface ComplaintRepository:JpaRepository<edu.spring.btp.entities.Complaint, Int> {
    fun findByDomain(domain: Domain): List<Complaint>

    companion object {
        fun findByDomain(domain: Domain) {

        }
    }
}