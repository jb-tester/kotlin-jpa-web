package com.mytests.spring.data

import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(idClass = Int::class, domainClass = Person::class)
interface PersonRepo {

    // derived queries:
    fun findPersonByIdAndName(id: Int, name: String): Person?
    fun searchByNameAndSurname(name: String, surname: String): List<Person>

    // named queries:
    fun personByLastName(name: String): List<Person>
    fun native2(name: String): List<Person>

}