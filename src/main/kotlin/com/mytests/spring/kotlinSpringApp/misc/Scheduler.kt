package com.mytests.spring.kotlinSpringApp.misc

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class Scheduler {

    @Scheduled(cron = "0 * * * * ?")
    fun foo() {
        println("!!!!!!!!!!!!!!!!!")
        println("!!!!!!!!!!!!!!!!!")
        println("!!!!!!!!!!!!!!!!!")
    }
}