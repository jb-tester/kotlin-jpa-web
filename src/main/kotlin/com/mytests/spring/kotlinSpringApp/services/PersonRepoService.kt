package com.mytests.spring.kotlinSpringApp.services

import com.mytests.spring.data.PersonRepo
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.stereotype.Service


@Service
class PersonRepoService(private val personRepo: PersonRepo) {

    fun findPersonByIdAndName(id:Int, name:String): Person?{
        return personRepo.findPersonByIdAndName(id, name)
    }
    fun findPeopleByLastname(lastname: String): List<Person>{
        return personRepo.personByLastName(lastname)
    }
    fun native2(name: String): List<Person> {
        return personRepo.native2(name)
    }
}
