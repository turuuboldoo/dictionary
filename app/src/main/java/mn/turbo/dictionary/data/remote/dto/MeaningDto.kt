package mn.turbo.dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName
import mn.turbo.dictionary.domain.model.Meaning

data class MeaningDto(
    @SerializedName("antonyms")
    val antonyms: List<Any>,
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
) {
    fun toMeaning() =
        Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
}
