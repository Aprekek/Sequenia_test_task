package ru.sequenia.testtask.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

@Dao
interface GenreFilmLinksDao {

	@Insert(onConflict = IGNORE)
	suspend fun insert(links: List<GenreFilmLinksDto>)
}