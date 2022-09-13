package mn.turbo.dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val word: String
)