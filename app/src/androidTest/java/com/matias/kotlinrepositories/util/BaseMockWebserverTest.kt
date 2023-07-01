package com.matias.kotlinrepositories.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
abstract class BaseMockWebserverTest : BaseIdlingTest() {

    protected val mockWebServer = MockWebServer()

    private lateinit var requestDispatcher: RequestDispatcher

    @Before
    override fun setUp() {
        super.setUp()
        mockWebServer.start(8080)
        requestDispatcher = RequestDispatcher()
        mockWebServer.dispatcher = requestDispatcher
    }

    @After
    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    protected fun disableDispatcher() {
        requestDispatcher.isEnabled = false
    }
}
