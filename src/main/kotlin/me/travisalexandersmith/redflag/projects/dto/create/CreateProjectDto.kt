package me.travisalexandersmith.redflag.projects.dto.create

import javax.validation.constraints.NotBlank

data class CreateProjectDto(
    @field:NotBlank(message = "Name is mandatory.")
    val name: String,

    val description: String?
)