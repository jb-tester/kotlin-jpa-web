package com.mytests.spring.kotlinSpringApp.services

import com.mytests.spring.data.FamilyRepository
import com.mytests.spring.data.PersonRepository
import com.mytests.spring.kotlinSpringApp.model.Family
import com.mytests.spring.kotlinSpringApp.model.Person
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.annotation.Transactional

@Configuration
class DataLoader {
    
//   This bean is only activated when the "manual-data-load" profile is active
//   By default, it's not used since we have data.sql for initial data loading
//   To use this instead of data.sql, add spring.profiles.active=manual-data-load to application.properties
    @Bean
    @Profile("manual-data-load")
    @Transactional
    fun databaseLoader(
        familyRepository: FamilyRepository,
        personRepository: PersonRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            // Check if we already have data
            val count = familyRepository.count()
            if (count <= 5L) {
                println("Loading families and persons into database...")
                

                val petrovFamily = familyRepository.save(Family("Petrov", "123 Lenina St, Moscow"))
                val sidorovFamily = familyRepository.save(Family("Sidorov", "456 Lenina, StPetersburg"))
                val kozlovFamily = familyRepository.save(Family("Kozlov", "789 Stroitelej, Moscow"))
                val smirnovFamily = familyRepository.save(Family("Smirnov", "321 Stroitelej, StPetersburg"))
                val pavlovFamily = familyRepository.save(Family("Pavlov", "654 Lenina, Novosibirsk"))
                
                addFamilyMembers(personRepository, petrovFamily, listOf(
                    Triple("Petr", "Petrov", petrovFamily),
                    Triple("Elena", "Petrov", petrovFamily),
                    Triple("Michael", "Petrov", petrovFamily)
                ))
                
                addFamilyMembers(personRepository, sidorovFamily, listOf(
                    Triple("Sidor", "Sidorov", sidorovFamily),
                    Triple("Ludmila", "Sidorov", sidorovFamily)
                ))
                
                addFamilyMembers(personRepository, kozlovFamily, listOf(
                    Triple("Daniil", "Kozlov", kozlovFamily),
                    Triple("Svetlana", "Kozlov", kozlovFamily)
                ))
                
                addFamilyMembers(personRepository, smirnovFamily, listOf(
                    Triple("Evgenij", "Smirnov", smirnovFamily),
                    Triple("Alla", "Smirnov", smirnovFamily)
                ))
                
                addFamilyMembers(personRepository, pavlovFamily, listOf(
                    Triple("Pavel", "Pavlov", pavlovFamily),
                    Triple("Olga", "Pavlov", pavlovFamily)
                ))
                
                println("Database has been populated with sample data. The entries amount is "+familyRepository.count())
            } else {
                println("Database already contains data. " + count + " entries found. Skipping initialization.")
            }
        }
    }
    
    private fun addFamilyMembers(
        personRepository: PersonRepository,
        family: Family,
        members: List<Triple<String, String, Family>>
    ) {
        members.forEach { (firstName, lastName, family) ->
            val person = Person(firstName, lastName, family, false )
            personRepository.save(person)
        }
    }
}
