package me.travisalexandersmith.redflag.projects

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.travisalexandersmith.redflag.projects.db.ProjectRepository
import me.travisalexandersmith.redflag.projects.db.entities.Project
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import me.travisalexandersmith.redflag.projects.errors.DuplicateProjectException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class ProjectServiceTest {

    private lateinit var projectRepository: ProjectRepository
    private val projectName = "Test Project"
    private val projectDescription = "This is a sample project."
    private val createProjectDto = CreateProjectDto(name = projectName, description = projectDescription)
    private lateinit var projectService: ProjectService

    @BeforeEach
    fun setup() {
        projectRepository = mockk<ProjectRepository>(relaxed = true)

        every { projectRepository.save(any()) } returns Project(id = 1, name = projectName)

        projectService = ProjectService(projectRepository = projectRepository)
    }

    @Test
    fun `ProjectService can be initialized without error`() {
        assertDoesNotThrow { ProjectService(projectRepository = projectRepository) }
    }

    @Test
    fun `ProjectService use the ProjectRepository to save newly created projects`() {
        every{ projectRepository.findByName(projectName) } returns null

        val createProjectResponseDto = projectService.createProject(createProjectDto)

        verify(exactly = 1) { projectRepository.save(any()) }

        assertEquals(1, createProjectResponseDto.id, "responds with created project's id")
        assertEquals(projectName, createProjectResponseDto.name, "responds with created project's name" )
    }

    @Test
    fun `ProjectService throws an exception when a duplicate project name is supplied`(){
        every { projectRepository.findByName(projectName) } returns Project(1, projectName)

        assertThrows<DuplicateProjectException> { projectService.createProject(createProjectDto)  }
    }
}