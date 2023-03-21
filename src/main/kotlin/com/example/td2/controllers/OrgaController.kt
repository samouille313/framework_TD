package com.example.td2.controllers

import com.example.td2.entities.Organization
import com.example.td2.entities.User
import com.example.td2.exceptions.ElementNotFoundException
import com.example.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import java.lang.Exception

@Controller
@RequestMapping("/orgas/")
class OrgaController {

    @Autowired
    lateinit var orgaRepository: OrganizationRepository

    @Autowired
    lateinit var orgaService:OrgaService


    @RequestMapping(path =["","index"])
    fun indexAction(model:ModelMap):String{
        model["orgas"]=orgaRepository.findAll()
        //+1/0
        return "/orgas/index"
    }


    @GetMapping("/new")
    fun newAction(model:ModelMap):String{
        model["orga"]=Organization()
        return "/orgas/form";
    }

    @PostMapping("/new")
    fun submitnewAction(
            @ModelAttribute orga:Organization,
            @ModelAttribute("users") users:String
    ):RedirectView{
        orgaService.addUsersFromString(users, orga)
        orgaRepository.save(orga)
        return  RedirectView("/orgas/")
    }

    @GetMapping("/delete/{id}")
    fun deleteAction(@PathVariable id:Int):RedirectView{
        orgaRepository.deleteById(id)
        return  RedirectView("/orgas/")
    }

    @GetMapping("/detail/{id}")
    fun detailAction(@PathVariable id:Int, model:ModelMap):String{
        val option = orgaRepository.findById(id)
        if(option.isPresent){
            model["orga"]=option.get()
            return "/orgas/detail"
        }
        throw ElementNotFoundException("Organisation d'id $id non trouvée !")
    }

}

