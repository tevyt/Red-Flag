package me.travisalexandersmith.redflag.projects.db

import me.travisalexandersmith.redflag.projects.db.entities.Project
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: CrudRepository<Project, Long>{
}