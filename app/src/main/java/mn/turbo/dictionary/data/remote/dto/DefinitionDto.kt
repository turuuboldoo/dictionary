package mn.turbo.dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName
import mn.turbo.dictionary.domain.model.Definition

data class DefinitionDto(
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String?
) {
    fun toDefinition() =
        Definition(
            definition = definition,
            example = example,
        )
}
