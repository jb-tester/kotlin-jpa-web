package com.mytests.spring.kotlinSpringApp.misc

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@EventListener
annotation class CustomEventListener

@Component
class Test {
    @CustomEventListener
    fun eventListener(event: Any) {
        println("CustomEventListener:")
        println(event)
        println("***********")
    }
}