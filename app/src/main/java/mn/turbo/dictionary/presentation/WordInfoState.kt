package mn.turbo.dictionary.presentation

import mn.turbo.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)