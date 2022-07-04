package com.example.simplereader.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAlphabetizedBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE file_path=:filePath")
    fun getBookByFilePath(filePath: String): Flow<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)
}