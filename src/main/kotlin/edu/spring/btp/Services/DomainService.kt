package edu.spring.btp.Services

import edu.spring.btp.entities.Domain
import edu.spring.btp.repositories.DomainRepository
import org.springframework.data.annotation.Id
import org.springframework.stereotype.Service

@Service
class DomainService(private val domainRepository: DomainRepository) {

    fun getDomainById(id:Int): Domain? {
        return domainRepository.findById(id).orElse(null)
    }

    fun getDomainsFromParent(parentId: Int): List<Domain>{
        return domainRepository.findByParentId(parentId)
    }

    fun getRootDomain():Domain{
        return domainRepository.findByParentIsNull().first()
    }

    fun getDomainByName(name:String): Domain? {
        return domainRepository.findByName(name)
    }
}