package com.mytests.spring.kotlinSpringApp.controller

import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Configuration
class ValueClassProblem {

    @JvmInline
    value class SomeInlineClass(val value: String)

    @RestController
    class SomeController {
        @GetMapping("/valueClassTest/test1")
        fun test1(@RequestBody param: String) = println(param)

        // https://youtrack.jetbrains.com/issue/IDEA-359482 - method is shown as unused with K2
        @GetMapping("/valueClassTest/test2")
        fun test2(@RequestBody param: SomeInlineClass) = println(param.value)
    }
}