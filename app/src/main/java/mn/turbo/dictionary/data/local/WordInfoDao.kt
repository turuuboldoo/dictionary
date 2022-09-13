package mn.turbo.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mn.turbo.dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    @Query("delete from word_info where word in(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("select * from word_info where word like '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>

}