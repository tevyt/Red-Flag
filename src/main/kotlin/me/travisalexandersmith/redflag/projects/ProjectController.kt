package me.travisalexandersmith.redflag.projects

import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class ProjectController @Autowired constructor(
    private val projectService: ProjectService
) {
    @PostMapping("/project")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createProjectDto: CreateProjectDto): CreateProjectResponseDto {
        return projectService.createProject(createProjectDto)
    }
}