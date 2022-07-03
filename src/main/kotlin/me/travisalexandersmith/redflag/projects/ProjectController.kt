package me.travisalexandersmith.redflag.projects

import me.travisalexandersmith.redflag.global.dto.GeneralErrorMessage
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectResponseDto
import me.travisalexandersmith.redflag.projects.errors.DuplicateProjectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
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

@ControllerAdvice
class ProjectControllerAdvice{

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DuplicateProjectException::class)
    fun handleDuplicateProjectException(ex: DuplicateProjectException): GeneralErrorMessage{
        return GeneralErrorMessage(message = ex.message ?: "a project already exists with this name.")
    }
}