package mn.turbo.dictionary.domain.repository

import kotlinx.coroutines.flow.Flow
import mn.turbo.dictionary.common.Resource
import mn.turbo.dictionary.domain.model.WordInfo

interface WordInfoRepository {

    fun getWordINfo(word: String): Flow<Resource<List<WordInfo>>>


}