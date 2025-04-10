package com.mytests.spring.kotlinSpringApp.misc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MyConfig(val myconfigprops: MyConfigProps, val nestedConfigProps: NestedConfigProps) {

    var greeting: String = myconfigprops.greeting+ nestedConfigProps.inner?.name

    @Bean
    open fun utilsBean(): UtilsBean {

        return UtilsBean(greeting)
    }


}

