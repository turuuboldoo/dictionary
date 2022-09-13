package mn.turbo.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import mn.turbo.dictionary.domain.model.Meaning
import mn.turbo.dictionary.domain.model.WordInfo

@Entity(tableName = "word_info")
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val word: String,
    @PrimaryKey
    val id: Int? = null
) {
    fun toWordInfo() =
        WordInfo(
            word = word,
            meanings = meanings,
        )
}
