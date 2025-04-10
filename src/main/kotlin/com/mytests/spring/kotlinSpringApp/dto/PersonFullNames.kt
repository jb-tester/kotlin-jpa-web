package com.mytests.spring.kotlinSpringApp.dto

import org.springframework.beans.factory.annotation.Value


interface PersonFullNames {
    @get:Value("#{target.name + ' ' + target.surname}")
    val fullName: String?
}
