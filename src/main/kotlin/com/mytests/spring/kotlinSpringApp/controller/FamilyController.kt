package com.mytests.spring.kotlinSpringApp.controller

import com.mytests.spring.kotlinSpringApp.model.Family
import com.mytests.spring.kotlinSpringApp.model.Person
import com.mytests.spring.kotlinSpringApp.services.FamilyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.collections.isNotEmpty

@RestController
@RequestMapping("/api/families")
class FamilyController(private val familyService: FamilyService) {
    
    @GetMapping
    fun getAllFamilies(): ResponseEntity<List<Family>> {
        val families = familyService.getAllFamilies()
        return ResponseEntity.ok(families)
    }
    
    @GetMapping("/{id}")
    fun getFamilyById(@PathVariable id: Int): ResponseEntity<Family> {
        val family = familyService.getFamilyById(id)
        return if (family != null) {
            ResponseEntity.ok(family)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/{id}/members")
    fun getFamilyMembers(@PathVariable id: Int): ResponseEntity<List<Person>> {
        val members = familyService.getFamilyMembers(id)
        return ResponseEntity.ok(members)
    }
    
    @GetMapping("/byName")
    fun getFamiliesByName(@RequestParam name: String): ResponseEntity<List<Family>> {
        val families = familyService.getFamiliesByName(name)
        return ResponseEntity.ok(families)
    }
    
    @GetMapping("/byAddress")
    fun getFamiliesByAddress(@RequestParam address: String): ResponseEntity<List<Family>> {
        val families = familyService.getFamiliesByAddress(address)
        return ResponseEntity.ok(families)
    }
    
    @GetMapping("/members/byFamilyName")
    fun getFamilyMembersByFamilyName(@RequestParam familyName: String): ResponseEntity<List<Person>> {
        val members = familyService.getFamilyMembersByFamilyName(familyName)
        return ResponseEntity.ok(members)
    }
    
    @GetMapping("/search/members")
    fun searchFamilyMembers(
        @RequestParam(required = false) familyId: Int?,
        @RequestParam(required = false) familyName: String?
    ): ResponseEntity<List<Person>> {
        val members = when {
            familyId != null -> familyService.getFamilyMembers(familyId)
            familyName != null -> familyService.getFamilyMembersByFamilyName(familyName)
            else -> emptyList()
        }
        
        return if (members.isNotEmpty()) {
            ResponseEntity.ok(members)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @PostMapping
    fun createFamily(@RequestBody family: Family): ResponseEntity<Family> {
        val savedFamily = familyService.saveFamily(family)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamily)
    }
    
    @PutMapping("/{id}")
    fun updateFamily(@PathVariable id: Int, @RequestBody family: Family): ResponseEntity<Family> {
        val existingFamily = familyService.getFamilyById(id)
        
        return if (existingFamily != null) {
            family.id = id
            val updatedFamily = familyService.saveFamily(family)
            ResponseEntity.ok(updatedFamily)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun deleteFamily(@PathVariable id: Int): ResponseEntity<Void> {
        familyService.deleteFamily(id)
        return ResponseEntity.noContent().build()
    }
    
    @PostMapping("/{familyId}/members")
    fun addPersonToFamily(@PathVariable familyId: Int, @RequestBody person: Person): ResponseEntity<Family> {
        val updatedFamily = familyService.addPersonToFamily(familyId, person)
        
        return if (updatedFamily != null) {
            ResponseEntity.ok(updatedFamily)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/stats")
    fun getFamilyStatistics(): ResponseEntity<Map<String, Any>> {
        val stats = familyService.getFamilyStatistics()
        return ResponseEntity.ok(stats)
    }
}
