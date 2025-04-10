package com.mytests.spring.kotlinSpringApp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "person")
@NamedQueries(value = [
NamedQuery(name = "Person.personByName", query = "select p.id, p.surname from Person p where p.name = ?1"),
NamedQuery(name = "Person.personByLastName", query = "select p.id, p.surname from Person p where p.surname = ?1")])
@NamedNativeQueries( value = [
    NamedNativeQuery(name = "Person.native1", query = "select id, surname from person  where firstname = ?1"),
    NamedNativeQuery(name = "Person.native2", query = "select * from person  where lastname = ?1"),
])
class Person {
    @Id
    @GeneratedValue
    var id: Int? = null

    @Column(name = "firstname")
    var name: String? = null

    @Column(name = "lastname")
    var surname: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @JsonIgnore
    var family: Family? = null

    constructor()

    constructor(name: String?, surname: String?) {
        this.name = name
        this.surname = surname
    }
    
    constructor(name: String?, surname: String?, family: Family?) {
        this.name = name
        this.surname = surname
        this.family = family
    }

    override fun toString(): String {
        return "id=" + id +
                ": name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", family='" + family?.name + '\''
    }
}
