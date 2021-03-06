package me.travisalexandersmith.redflag.projects

import me.travisalexandersmith.redflag.projects.db.ProjectRepository
import me.travisalexandersmith.redflag.projects.db.entities.Project
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectResponseDto
import me.travisalexandersmith.redflag.projects.errors.DuplicateProjectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository
) {

    fun createProject(createProjectDto: CreateProjectDto): CreateProjectResponseDto {
        if (projectRepository.findByName(createProjectDto.name) != null) {
            throw DuplicateProjectException("project already exists with the name: ${createProjectDto.name}")
        } else {
            val project = Project(id = null, name = createProjectDto.name, description = createProjectDto.description)

            val savedProject = projectRepository.save(project)

            return CreateProjectResponseDto(
                id = savedProject.id as Long,
                name = savedProject.name as String,
                description = savedProject.description
            )
        }

    }
}