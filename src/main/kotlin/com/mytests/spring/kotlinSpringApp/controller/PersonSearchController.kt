package com.mytests.spring.kotlinSpringApp.controller

import com.mytests.spring.data.FamilyRepository
import com.mytests.spring.data.PersonRepository
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/persons")
class PersonSearchController(
    private val personRepository: PersonRepository,
    private val familyRepository: FamilyRepository
) {

    @GetMapping("/search")
    fun searchPersons(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) surname: String?,
        @RequestParam(required = false) familyId: Int?,
        @RequestParam(required = false) familyName: String?
    ): ResponseEntity<List<Person>> {

        val persons = when {
            // Search by family ID
            familyId != null -> {
                personRepository.findByFamilyId(familyId)
            }
            // Search by family name
            familyName != null -> {
                personRepository.findByFamilyName(familyName)
            }
            // Search by person name and surname
            name != null && surname != null -> {
                personRepository.findPersonByNameAndSurname(name, surname)
            }
            // Search by person name only
            name != null -> {
                personRepository.personByName(name)
            }
            // No search criteria provided
            else -> {
                personRepository.findAll().toList()
            }
        }

        return ResponseEntity.ok(persons)
    }

    @GetMapping("/family-members")
    fun findFamilyMembers(
        @RequestParam(required = false) familyId: Int?,
        @RequestParam(required = false) familyName: String?
    ): ResponseEntity<Map<String, Any>> {

        if (familyId == null && familyName == null) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Either familyId or familyName parameter is required"))
        }

        val result = mutableMapOf<String, Any>()

        // Find family and members
        when {
            familyId != null -> {
                val family = familyRepository.findById(familyId).orElse(null)
                if (family != null) {
                    val members = personRepository.findByFamilyId(familyId)
                    result["family"] = family
                    result["members"] = members
                    result["memberCount"] = members.size
                } else {
                    return ResponseEntity.notFound().build()
                }
            }
            familyName != null -> {
                val families = familyRepository.findByName(familyName)
                if (families.isNotEmpty()) {
                    val family = families.first()
                    val members = personRepository.findByFamilyName(familyName)
                    result["family"] = family
                    result["members"] = members
                    result["memberCount"] = members.size
                } else {
                    return ResponseEntity.notFound().build()
                }
            }
        }

        return ResponseEntity.ok(result)
    }
}