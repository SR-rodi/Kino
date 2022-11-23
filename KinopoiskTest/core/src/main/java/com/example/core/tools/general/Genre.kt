package com.example.core.tools.general

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
class Genre(
    @ColumnInfo(name = "info")
    @SerializedName("genre") override val info: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int
): GenreCountry