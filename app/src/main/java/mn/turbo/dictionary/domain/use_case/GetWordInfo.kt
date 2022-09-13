package mn.turbo.dictionary.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mn.turbo.dictionary.common.Resource
import mn.turbo.dictionary.domain.model.WordInfo
import mn.turbo.dictionary.domain.repository.WordInfoRepository
import javax.inject.Inject

class GetWordInfo constructor(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow { }
        }

        return repository.getWordINfo(word)
    }

}