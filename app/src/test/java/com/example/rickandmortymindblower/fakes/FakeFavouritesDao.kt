package com.example.rickandmortymindblower.fakes

import com.example.rickandmortymindblower.data.db.FavouriteEntity
import com.example.rickandmortymindblower.data.db.FavouritesDao
import com.example.rickandmortymindblower.testbuilder.FavouriteEntityBuilder.aFavouriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

class FakeFavouritesDao : FavouritesDao {

    private var getByIdReturns: FavouriteEntity? = null

    private var insertCalledWith: FavouriteEntity? = null
    private var deleteByIdCalledWith: String? = null

    @BeforeEach
    fun setup() {
        getByIdReturns = null

        insertCalledWith = null
        deleteByIdCalledWith = null
    }

    override fun getAll(): Flow<List<FavouriteEntity>> {
        return flowOf(listOf(aFavouriteEntity()))
    }

    fun givenFavouriteWithId(id: String) {
        getByIdReturns = aFavouriteEntity(id = id)
    }

    override fun getById(id: String): FavouriteEntity? {
        return getByIdReturns
    }

    override suspend fun insert(favouriteEntity: FavouriteEntity) {
        insertCalledWith = favouriteEntity
    }

    fun verifyInsert(favouriteEntity: FavouriteEntity) {
        assertEquals(insertCalledWith, favouriteEntity)
    }

    override suspend fun deleteById(id: String) {
        deleteByIdCalledWith = id
    }

    fun verifyDeleteById(id: String) {
        assertEquals(deleteByIdCalledWith, id)
    }
}