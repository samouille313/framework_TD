package edu.spring.btp.controllers

import edu.spring.btp.Services.ComplaintService
import edu.spring.btp.Services.DomainService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IndexController {


    lateinit var domainService: DomainService
    lateinit var complaintService : ComplaintService

    @GetMapping(value = ["/", "", "/index"])
    fun index(model: Model): String {
        val rootDomain = domainService.getRootDomain() // Récupère le domaine racine
        val domains = domainService.getDomainsFromParent(rootDomain.id) // Récupère les domaines enfants

        model.addAttribute("rootDomain", rootDomain)
        model.addAttribute("domains", domains)

        return "index"
    }

    @GetMapping("/domain/{name}")
    fun getChildDomains(@PathVariable name: String, model: Model): String {
        val domain = domainService.getDomainByName(name) // Récupère le domaine parent par son nom
        val childDomains = domain?.let { domainService.getDomainsFromParent(it.id) } // Récupère les domaines enfants

        model.addAttribute("domain", domain)
        model.addAttribute("childDomains", childDomains)

        return "index"
    }

    @GetMapping("/complaints/{domain}")
    fun listComplaints(@PathVariable domain: String, model: Model): String {
        val domainObj = domainService.getDomainByName(domain)
        val complaints = domainObj?.let { complaintService.getComplaintsByDomain(it) }

        model.addAttribute("complaints", complaints)

        return "complaints"
    }
}