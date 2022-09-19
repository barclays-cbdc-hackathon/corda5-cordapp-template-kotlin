package com.cbdc.industria.tech.bridge

import com.cbdc.industria.tech.bridge.services.HOST_URL
import com.cbdc.industria.tech.bridge.services.SandboxEnvService
import com.cbdc.industria.tech.bridge.services.THREADS_COUNT
import net.corda.v5.base.concurrent.getOrThrow
import java.util.concurrent.Executors
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class SandboxEnvServiceTests {
    private val sandboxEnvService = SandboxEnvService(
        executor = Executors.newFixedThreadPool(THREADS_COUNT),
        host = HOST_URL
    )

    private var environmentId: Long = 0

    @BeforeEach
    fun setup() {
        environmentId = sandboxEnvService.createEnvironment()
    }

    @AfterEach
    fun tearDown() {
        sandboxEnvService.deleteEnv(environmentId)
    }

    @Test
    fun `post environment`() {
        assertThat(sandboxEnvService.getEnvsWithoutTerminated().size).isEqualTo(1)
    }

    @Test
    fun `get environment`() {
        val getEnvFuture = sandboxEnvService.getEnv(environmentId)
        val getEnv = getEnvFuture.getOrThrow()
        assertThat(getEnv.data.id).isEqualTo(environmentId)
    }
}
