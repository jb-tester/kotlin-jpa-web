package com.mytests.spring.kotlinSpringApp.services

import com.mytests.spring.data.PersonRepository
import com.mytests.spring.kotlinSpringApp.misc.UtilsBean
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val utilsBean: UtilsBean

) {

    val allPersonNames: List<String?>
        get() {
            val list: MutableList<String?> = ArrayList()
            for (personDTO in personRepository.findAllBy()) {
                list.add(personDTO.fullName)
            }
            return list
        }
    fun findById(id: Int): String? {
        return personRepository.findById(id).get().toString()
    }
    fun findByNameAndSurname(name: String, surname: String): List<String> {

        val list: MutableList<String> = ArrayList(
            java.util.List.of(
                utilsBean.greeting
            )
        )
        for (person in personRepository.findPersonByNameAndSurname(name, surname)) {
            list.add(person.toString())
        }
        return list
    }
    fun findAllPersonLastnames(): List<String> {

        return personRepository.findPeopleBy().map { it.getSurname() }
    }
    fun deleteBySurname(surname: String) {

        personRepository.deletePersonBySurname(surname)
    }

}
