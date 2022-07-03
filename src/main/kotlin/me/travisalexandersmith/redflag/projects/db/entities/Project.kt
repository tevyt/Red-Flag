package me.travisalexandersmith.redflag.projects.db.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.UniqueConstraint

@Entity
data class Project(

    @Id
    @GeneratedValue
    val id: Long? = 0,

    @Column(nullable = false, unique = true)
    val name: String? = "",

    val description: String? = ""
)

