package com.mytests.spring.data

import com.mytests.spring.kotlinSpringApp.model.Family
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FamilyRepository : CrudRepository<Family, Int> {
    fun findByName(name: String): List<Family>
    fun findByAddress(address: String): List<Family>
}