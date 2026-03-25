package com.mytests.spring.kotlinSpringApp.controller

import com.mytests.spring.kotlinSpringApp.model.Person
import com.mytests.spring.kotlinSpringApp.services.PersonRepoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

/**
 **
 * <p>Created by Irina on 3/25/2026.</p>
 **
 */
@RestController
@RequestMapping("/")
class PersonRepoController(private val personRepoService: PersonRepoService) {
    @GetMapping("/byIdAndName/{id}-{name}")
    fun byIdAndName(@PathVariable id: Int, @PathVariable name: String): Person? {
        return personRepoService.findPersonByIdAndName(id,name)
    }

    @GetMapping("/byLastName/{lastName}")
    fun byLastName(@PathVariable lastName: String):List<Person> {
        return personRepoService.findPeopleByLastname(lastName)
    }
}
