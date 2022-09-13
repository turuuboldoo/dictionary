package mn.turbo.dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName
import mn.turbo.dictionary.data.local.entity.WordInfoEntity
import mn.turbo.dictionary.domain.model.WordInfo

data class WordInfoDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("word")
    val word: String
) {
    fun toWordInfo() =
        WordInfo(
            meanings = meanings.map { it.toMeaning() },
            word = word
        )

    fun toWordInfoEntity() =
        WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            word = word
        )
}
