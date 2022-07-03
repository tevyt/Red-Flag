package me.travisalexandersmith.redflag.projects

import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectResponseDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals

class ProjectControllerTest {

    private lateinit var projectController: ProjectController
    private lateinit var projectService: ProjectService
    private val createProjectDto = CreateProjectDto(name = "Test Project", description = "A sample project")

    @BeforeEach
    fun setup() {
        projectService = mockkClass(ProjectService::class)
        every { projectService.createProject(createProjectDto) } returns
                CreateProjectResponseDto(id = 1, name = createProjectDto.name, description = createProjectDto.description)
        projectController = ProjectController(projectService = projectService)
    }

    @Test
    fun `ProjectController can be initialized`() {
        assertDoesNotThrow { ProjectController( projectService = projectService) }
    }

    @Test
    fun `create uses the ProjectService to create a project`(){
        projectController.create(createProjectDto)

        verify { projectService.createProject(createProjectDto) }
    }

    @Test
    fun `create response with the created project`(){
       val createProjectResponse = projectController.create(createProjectDto)

        assertEquals(createProjectDto.name, createProjectResponse.name)
        assertEquals(createProjectDto.description, createProjectResponse.description)
    }

}