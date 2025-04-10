package com.mytests.spring.kotlinSpringApp.controller

import com.mytests.spring.kotlinSpringApp.services.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first")
class PersonController(private val personService: PersonService) {

    @GetMapping("/all")
    fun findAll(): List<String?> {
        return personService.allPersonNames
    }

    @GetMapping("/allSurnames")
    fun findAllSurnames(): List<String?> {
        return personService.findAllPersonLastnames()
    }

    @GetMapping("/byId/{id:\\d+}")
    fun test1(@PathVariable id: Int): String? {
        return personService.findById(id);
    }

    @GetMapping("/byNameAndSurname/{name}/{surname}")
    fun test2(@PathVariable name: String, @PathVariable surname: String): List<String> {

        return personService.findByNameAndSurname(name,surname);
    }

    @GetMapping("/delete")
    fun deleteSurnames(): List<String?> {
        personService.deleteBySurname("Smith")
        return personService.findAllPersonLastnames()
    }


}