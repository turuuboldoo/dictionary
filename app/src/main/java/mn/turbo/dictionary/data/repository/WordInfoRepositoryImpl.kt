package mn.turbo.dictionary.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mn.turbo.dictionary.common.Resource
import mn.turbo.dictionary.data.local.WordInfoDao
import mn.turbo.dictionary.data.remote.DictionaryApi
import mn.turbo.dictionary.domain.model.WordInfo
import mn.turbo.dictionary.domain.repository.WordInfoRepository
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordINfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "HttpException!!!  ${e.localizedMessage}",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "IOException!!!  ${e.localizedMessage}",
                    data = wordInfos
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
