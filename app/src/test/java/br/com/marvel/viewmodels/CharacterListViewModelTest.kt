package br.com.marvel.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marvel.CoroutineTestRule
import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse
import br.com.marvel.models.Thumbnail
import br.com.marvel.repositories.MarvelRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class CharacterListViewModelTest {

    private lateinit var repositoryMock: MarvelRepository
    private lateinit var viewModel: CharacterListViewModel
    private lateinit var coroutineContextMock: CoroutineContext

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repositoryMock = mock()
        coroutineContextMock = coroutineTestRule.testDispatcher
        viewModel = CharacterListViewModel(repositoryMock, coroutineContextMock)
    }

    @Test
    fun `Get Marvel characters, then assert that livedata is filled`() {

        // Arrange
        val thumbnail = Thumbnail(
            path = "thumbnail path",
            extension = "jpg"
        )

        val character = Character(
            id = 1,
            name = "Character name",
            description = "Character description",
            thumbnail = thumbnail
        )
        val expectedResult = MarvelResponse(
            code = 200,
            data = MarvelResponse.Data(
                results = listOf(character)
            )
        )

        whenever(
            runBlocking(coroutineContextMock) {
                repositoryMock.getCharactersList(any())
            }
        ).thenReturn(expectedResult)

        // Act
        viewModel.getCharactersList()

        // Assert
        assertEquals(expectedResult, viewModel.characters.value)
    }

    @Test
    fun `Get Marvel characters, then handle error response`() {

        // Arrange
        val exceptionMessage = "Error description"

        whenever(
            runBlocking(coroutineContextMock) {
                repositoryMock.getCharactersList(any())
            }
        ).then {
            throw Exception(exceptionMessage)
        }

        // Act
        viewModel.getCharactersList()

        // Assert
        assertEquals(exceptionMessage, viewModel.error.value)
    }

}