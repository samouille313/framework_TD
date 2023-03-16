package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView

@Controller
class MainController {

    @RequestMapping("/")
    fun indexAction():String{
        return "index"
    }

    @PostMapping("/master/add")
    fun addMaster(@RequestParam(name = "firstname") firstname: String,
                  @RequestParam(name = "lastname") lastname: String,
                  ):String{
        return "/"
    }





}
