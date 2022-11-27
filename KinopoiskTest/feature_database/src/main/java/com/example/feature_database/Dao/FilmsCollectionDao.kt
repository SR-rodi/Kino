package com.example.feature_database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.feature_database.entity.FilmsCollectionEntity
import com.example.feature_database.entity.MergeCollectionAndFilms
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsCollectionDao {

    @Query("SELECT merge.film_id_collection  " +
            "FROM films_collections " +
            "INNER JOIN merge " +
            "ON id = collection_id " +
            "WHERE collection_id =:collectionID ")
    fun getFilmsFromCollectionID(collectionID: Int = 1): List <Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMergeCollectionAndFilms(merge: MergeCollectionAndFilms):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCollection(collection: FilmsCollectionEntity):Long

    @Query("SELECT*FROM films_collections")
    fun getCollection(): Flow<List<FilmsCollectionEntity>>

    @Update
    fun updateCollection(collection: FilmsCollectionEntity):Int

}