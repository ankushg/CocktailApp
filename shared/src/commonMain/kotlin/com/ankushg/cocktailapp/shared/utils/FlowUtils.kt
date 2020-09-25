package com.ankushg.cocktailapp.shared.utils

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * Based on helper functions from KotlinConf app:
 * https://github.com/JetBrains/kotlinconf-app/blob/d56c3383d52dfdc32ba449d8daf9b9f633dd1d08/common/src/mobileMain/kotlin/org/jetbrains/kotlinconf/FlowUtils.kt
 */

internal fun <T> StateFlow<T>.wrap(scope: CoroutineScope): CStateFlow<T> = CStateFlow(this, scope)

class CStateFlow<T>(
    private val origin: StateFlow<T>,
    private val scope: CoroutineScope
) : StateFlow<T> by origin {
    /**
     * Designed to allow iOS clients to properly end the job
     */
    fun consumeAsCloseable(completionHandler: (T?, Throwable?) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        (scope + job).launch {
            try {
                onEach {
                    completionHandler(it, null)
                }
            } catch (e: Throwable) {
                completionHandler.invoke(null, e)
            }
        }

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}