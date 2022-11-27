package com.example.feature_database.entity

import androidx.room.*

@Entity(
    tableName = "films_collections",
    indices = [Index("name", unique = true)]
)
class FilmsCollectionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val nameCollection: String,
    @ColumnInfo(name = "size")
    var size: Int = 0
)


/*const collections = [
{name: 'Favorite', id:1},
{name: 'Trillers', id:2},
{name: 'Horrors', id:3},
{name: 'Comedies', id:4},
]

const mapping = [
{ collectionId: 1, filmId: 'aaa'},
{ collectionId: 3, filmId: 'asasas'},
{ collectionId: 3, filmId: 'bbb'},
{ collectionId: 4, filmId: 'ccc'},
{ collectionId: 4, filmId: 'ddd'},
]

const groupsByCollectionId = {}

mapping.forEach(({ collectionId, filmId }) => {
    if (groupsByCollectionId[collectionId]) {
        groupsByCollectionId[collectionId].push(filmId)
    } else {
        groupsByCollectionId[collectionId] = [filmId]
    }
});


const collectionsFinal = collections.map((collection) => {
    return {
        ...collection,
        films: groupsByCollectionId[collection.id] ? groupsByCollectionId[collection.id].length : 0
    }
});*/
