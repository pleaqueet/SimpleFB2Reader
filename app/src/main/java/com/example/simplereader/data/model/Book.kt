package com.example.simplereader.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    @ColumnInfo(name = "file_path")
    val filePath: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "file_format")
    val fileFormat: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "body")
    val body: String
)
