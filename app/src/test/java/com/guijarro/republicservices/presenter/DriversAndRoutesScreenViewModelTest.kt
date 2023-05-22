package com.guijarro.republicservices.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.guijarro.republicservices.domain.usecases.GetDriversAndRoutsUseCase
import com.guijarro.republicservices.domain.usecases.GetLocalDriversAndRoutesUseCase
import com.guijarro.republicservices.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class DriversAndRoutesScreenViewModelTest {

//    //Mock the main thread and execute all the task
//    @get:Rule val rule = InstantTaskExecutorRule()

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    private lateinit var testObject: DriversAndRoutesScreenViewModel
    private val mockRemoteUseCase = mockk<GetDriversAndRoutsUseCase>(relaxed = true)
    private val mockLocalUseCase = mockk<GetLocalDriversAndRoutesUseCase>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        testObject = DriversAndRoutesScreenViewModel(mockRemoteUseCase, mockLocalUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    /**
     * test get drivers when usecase provides success data and it returns a success state
     */
    @Test
    fun `get Your Driver`() {
        //initial state
        val initState = testObject.drivers

        every {
            mockRemoteUseCase()
        } returns flowOf(
            UIState.Success(
                listOf(mockk(relaxed = true))
            )
        )

        testObject.getDriversAndRoutes()

        val newState = testObject.drivers


        assertEquals(newState, initState)
        assertEquals(3,testObject.drivers.value)

    }
}
 //Reusable JUnit4 TestRule to override the Main dispatcher
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
