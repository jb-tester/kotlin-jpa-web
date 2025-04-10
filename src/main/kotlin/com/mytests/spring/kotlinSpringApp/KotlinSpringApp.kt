package com.mytests.spring.kotlinSpringApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
@EnableJpaRepositories(basePackages = ["com.mytests.spring.data"])
@ImportResource("classpath:spring-context.xml")
open class KotlinSpringApp

fun main(args: Array<String>) {
    runApplication<KotlinSpringApp>(*args)
}

