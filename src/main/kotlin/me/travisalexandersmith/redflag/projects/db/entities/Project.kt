package me.travisalexandersmith.redflag.projects.db.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Project(

    @Id
    @GeneratedValue
    val id: Long?,

    val name: String?
)