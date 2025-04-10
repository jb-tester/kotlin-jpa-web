package com.mytests.spring.kotlinSpringApp.services

import com.mytests.spring.data.FamilyRepository
import com.mytests.spring.data.PersonRepository
import com.mytests.spring.kotlinSpringApp.model.Family
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class DataLoader {
    
    /**
     * This bean is only activated when the "manual-data-load" profile is active
     * By default, it's not used since we have data.sql for initial data loading
     * To use this instead of data.sql, add spring.profiles.active=manual-data-load to application.properties
     */
    @Bean
    @Profile("manual-data-load")
    fun databaseLoader(
        familyRepository: FamilyRepository,
        personRepository: PersonRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            // Check if we already have data (from data.sql perhaps)
            if (familyRepository.count() == 0L) {
                println("Loading families and persons into database...")
                
                // Create families
                val smithFamily = familyRepository.save(Family("Smith", "123 Main St, New York"))
                val johnsonFamily = familyRepository.save(Family("Johnson", "456 Oak Ave, Chicago"))
                val williamsFamily = familyRepository.save(Family("Williams", "789 Pine Rd, Los Angeles"))
                val brownFamily = familyRepository.save(Family("Brown", "321 Cedar Ln, Houston"))
                val davisFamily = familyRepository.save(Family("Davis", "654 Maple Dr, Philadelphia"))
                
                // Create persons with family relationships
                addFamilyMembers(personRepository, smithFamily, listOf(
                    Triple("John", "Smith", smithFamily),
                    Triple("Jane", "Smith", smithFamily),
                    Triple("Michael", "Smith", smithFamily)
                ))
                
                addFamilyMembers(personRepository, johnsonFamily, listOf(
                    Triple("Robert", "Johnson", johnsonFamily),
                    Triple("Emily", "Johnson", johnsonFamily)
                ))
                
                addFamilyMembers(personRepository, williamsFamily, listOf(
                    Triple("David", "Williams", williamsFamily),
                    Triple("Sarah", "Williams", williamsFamily)
                ))
                
                addFamilyMembers(personRepository, brownFamily, listOf(
                    Triple("James", "Brown", brownFamily),
                    Triple("Emma", "Brown", brownFamily)
                ))
                
                addFamilyMembers(personRepository, davisFamily, listOf(
                    Triple("Daniel", "Davis", davisFamily),
                    Triple("Olivia", "Davis", davisFamily)
                ))
                
                println("Database has been populated with sample data.")
            } else {
                println("Database already contains data. Skipping initialization.")
            }
        }
    }
    
    private fun addFamilyMembers(
        personRepository: PersonRepository,
        family: Family,
        members: List<Triple<String, String, Family>>
    ) {
        members.forEach { (firstName, lastName, family) ->
            val person = Person(firstName, lastName, family)
            personRepository.save(person)
        }
    }
}
