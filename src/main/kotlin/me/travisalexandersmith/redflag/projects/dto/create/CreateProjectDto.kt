package me.travisalexandersmith.redflag.projects.dto.create

import javax.validation.constraints.NotBlank

data class CreateProjectDto(
    @field:NotBlank()
    val name: String = "Name is mandatory"
)