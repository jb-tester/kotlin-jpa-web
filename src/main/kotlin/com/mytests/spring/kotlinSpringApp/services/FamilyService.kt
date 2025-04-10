package com.mytests.spring.kotlinSpringApp.services

import com.mytests.spring.data.FamilyRepository
import com.mytests.spring.data.PersonRepository
import com.mytests.spring.kotlinSpringApp.model.Family
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FamilyService(
    private val familyRepository: FamilyRepository,
    private val personRepository: PersonRepository
) {
    
    fun getAllFamilies(): List<Family> = familyRepository.findAll().toList()
    
    fun getFamilyById(id: Int): Family? = familyRepository.findById(id).orElse(null)
    
    fun getFamilyMembersByFamilyName(familyName: String): List<Person> = personRepository.findByFamilyName(familyName)
    
    fun getFamiliesByName(name: String): List<Family> = familyRepository.findByName(name)
    
    fun getFamiliesByAddress(address: String): List<Family> = familyRepository.findByAddress(address)
    
    fun getFamilyMembers(familyId: Int): List<Person> = personRepository.findByFamilyId(familyId)
    
    @Transactional
    fun saveFamily(family: Family): Family = familyRepository.save(family)
    
    @Transactional
    fun deleteFamily(id: Int) {
        if (familyRepository.existsById(id)) {
            familyRepository.deleteById(id)
        }
    }
    
    @Transactional
    fun addPersonToFamily(familyId: Int?, person: Person): Family? {
        familyId?.let { id ->
            val family = familyRepository.findById(id).orElse(null)
            family?.let {
                person.family = it
                it.members.add(person)
                personRepository.save(person)
                return familyRepository.save(it)
            }
        }
        return null
    }
    
    fun getFamilyStatistics(): Map<String, Any> {
        val families = familyRepository.findAll().toList()
        val totalFamilies = families.size
        val totalMembers = personRepository.count()
        val averageFamilySize = if (totalFamilies > 0) totalMembers.toDouble() / totalFamilies else 0.0
        
        val familyData = families.map { family ->
            val members = personRepository.findByFamilyId(family.id ?: 0)
            mapOf(
                "id" to family.id,
                "name" to family.name,
                "address" to family.address,
                "memberCount" to members.size
            )
        }
        
        return mapOf(
            "totalFamilies" to totalFamilies,
            "totalMembers" to totalMembers,
            "averageFamilySize" to averageFamilySize,
            "families" to familyData
        )
    }
}
