package me.travisalexandersmith.redflag.projects

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.boot.test.context.SpringBootTest

class ProjectControllerTest {

    @Test
    fun `ProjectController can be initialized`(){
       assertDoesNotThrow { ProjectController() }
    }
}