package com.mytests.spring.kotlinSpringApp.misc

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("my.props")
class MyConfigProps {
    var greeting: String? = null
}
