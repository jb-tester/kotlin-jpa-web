package com.mytests.spring.kotlinSpringApp.misc

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "my.nested")
class NestedConfigProps {
    var name: String? = "default_name"
    var inner: NestedInner? = null

    class NestedInner {
        var name: String? = "???"
    }
}
