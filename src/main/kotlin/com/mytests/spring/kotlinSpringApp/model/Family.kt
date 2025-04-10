package com.mytests.spring.kotlinSpringApp.model

import jakarta.persistence.*

@Entity
@Table(name = "family")
class Family {
    @Id
    @GeneratedValue
    var id: Int? = null

    @Column(name = "name")
    var name: String? = null
    
    @Column(name = "address")
    var address: String? = null
    
    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var members: MutableList<Person> = mutableListOf()
    
    constructor()
    
    constructor(name: String?, address: String?) {
        this.name = name
        this.address = address
    }
    
    override fun toString(): String {
        return "Family(id=$id, name=$name, address=$address)"
    }
}
