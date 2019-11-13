package br.com.marvel.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marvel.CoroutineTestRule
import br.com.marvel.models.Comic
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
import kotlin.coroutines.CoroutineContext

class CharacterDetailViewModelTest {

    private lateinit var repositoryMock: MarvelRepository
    private lateinit var viewModel: CharacterDetailViewModel
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
        viewModel = CharacterDetailViewModel(repositoryMock, coroutineContextMock)
    }

    @Test
    fun `Get Marvel character comics, then assert that livedata is filled`() {

        // Arrange
        val thumbnail = Thumbnail(
            path = "thumbnail path",
            extension = "jpg"
        )

        val comic = Comic(
            id = 1,
            title = "Comic name",
            description = "Comic description",
            thumbnail = thumbnail
        )
        val expectedResult = MarvelResponse(
            code = 200,
            data = MarvelResponse.Data(
                results = listOf(comic)
            )
        )

        whenever(
            runBlocking(coroutineContextMock) {
                repositoryMock.getCharacterComicsList(any())
            }
        ).thenReturn(expectedResult)

        // Act
        viewModel.getCharacterComicsList(any())

        // Assert
        assertEquals(expectedResult, viewModel.comics.value)
    }

    @Test
    fun `Get Marvel character comics, then handle error response`() {

        // Arrange
        val exceptionMessage = "Error description"

        whenever(
            runBlocking(coroutineContextMock) {
                repositoryMock.getCharacterComicsList(any())
            }
        ).then {
            throw Exception(exceptionMessage)
        }

        // Act
        viewModel.getCharacterComicsList(any())

        // Assert
        assertEquals(exceptionMessage, viewModel.error.value)
    }

}