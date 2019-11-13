package br.com.marvel.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marvel.datasources.MarvelRemoteDataSource
import br.com.marvel.models.Character
import br.com.marvel.models.Comic
import br.com.marvel.models.MarvelResponse
import br.com.marvel.models.Thumbnail
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MarvelRepositoryTest {

    private lateinit var datasourceMock: MarvelRemoteDataSource
    private lateinit var repository: MarvelRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        datasourceMock = mock()
        repository = MarvelRepository(datasourceMock)
    }

    @Test
    fun `Get Marvel characters, then assert suspend function returns`() {

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
                offset = 0,
                limit = 20,
                total = 1493,
                count = 20,
                results = listOf(character)
            )
        )

        whenever(
            runBlocking {
                datasourceMock.getCharactersList(any())
            }
        ).thenReturn(expectedResult)

        // Act
        runBlocking {
            val result = repository.getCharactersList(any())
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `Get Marvel character comics, then assert suspend function returns`() {

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
                offset = 0,
                limit = 20,
                total = 1493,
                count = 20,
                results = listOf(comic)
            )
        )

        whenever(
            runBlocking {
                datasourceMock.getCharacterComicsList(any())
            }
        ).thenReturn(expectedResult)

        // Act
        runBlocking {
            val result = repository.getCharacterComicsList(any())
            assertEquals(expectedResult, result)
        }
    }
}