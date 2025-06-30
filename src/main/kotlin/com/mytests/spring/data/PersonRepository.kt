package com.mytests.spring.data

import com.mytests.spring.kotlinSpringApp.dto.PersonFullNames
import com.mytests.spring.kotlinSpringApp.dto.PersonSurnamesOnly
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface PersonRepository: CrudRepository<Person, Int> {
    // projection interfaces using:
    fun findAllBy(): List<PersonFullNames>
    fun findPeopleBy(): List<PersonSurnamesOnly>

    // named queries using:
    fun personByName(name: String): List<Person>
    fun native1(name: String): List<Person>

    // queries by conventional method name:
    fun findPersonByNameAndSurname(name: String, surname: String): List<Person>
   // fun findByName(): List<Person>

    // overridden queries:
    override fun count(): Long

    // custom queries
    @Query("select p from Person p where p.name != 'maria'")
    fun customQuery(): List<Person>

    fun deletePersonBySurname(surname: String)

    // Find persons by family
    fun findByFamilyId(familyId: Int): List<Person>
    
    // Find persons by family name using JPQL
    @Query("SELECT p FROM Person p JOIN p.family f WHERE f.name = :familyName")
    fun findByFamilyName(@Param("familyName") familyName: String): List<Person>
    
    // Alternative method using native SQL
    @Query(value = "SELECT p.* FROM person p JOIN family f ON p.family_id = f.id WHERE f.name = :familyName", nativeQuery = true)
    fun findByFamilyNameNative(@Param("familyName") familyName: String): List<Person>

    fun findByIsMarried(isMarried: Boolean): MutableList<Person>

}