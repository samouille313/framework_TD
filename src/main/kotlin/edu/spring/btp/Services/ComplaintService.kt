package edu.spring.btp.Services

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.Domain
import edu.spring.btp.repositories.ComplaintRepository

class ComplaintService {
    fun getComplaintsByDomain(domain: Domain) {
        return ComplaintRepository.findByDomain(domain)
    }

}