package me.travisalexandersmith.redflag.projects

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import me.travisalexandersmith.redflag.projects.db.ProjectRepository
import me.travisalexandersmith.redflag.projects.db.entities.Project
import me.travisalexandersmith.redflag.projects.dto.create.CreateProjectDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals

class ProjectServiceTest {

    private lateinit var projectRepository: ProjectRepository
    private val projectName = "Test Project"
    private val createProjectDto = CreateProjectDto(name = projectName)
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
        val createProjectResponseDto = projectService.createProject(createProjectDto)

        verify(exactly = 1) { projectRepository.save(any()) }

        assertEquals(1, createProjectResponseDto.id, "responds with created project's id")
        assertEquals(projectName, createProjectResponseDto.name, "responds with created project's name" )
    }
}