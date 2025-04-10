package com.mytests.spring.data

import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(idClass = Int::class, domainClass = Person::class)
interface PersonRepo {

    fun findPersonByIdAndName(id: Int, name: String): Person?
    fun personByLastName(name: String): List<Person>
    fun native2(name: String)

}