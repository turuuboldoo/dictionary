package mn.turbo.dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mn.turbo.dictionary.common.Constants
import mn.turbo.dictionary.data.local.Converters
import mn.turbo.dictionary.data.local.WordInfoDatabase
import mn.turbo.dictionary.data.remote.DictionaryApi
import mn.turbo.dictionary.data.repository.WordInfoRepositoryImpl
import mn.turbo.dictionary.data.util.GsonParser
import mn.turbo.dictionary.domain.repository.WordInfoRepository
import mn.turbo.dictionary.domain.use_case.GetWordInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(
        app: Application
    ): WordInfoDatabase {
        return Room
            .databaseBuilder(
                app.applicationContext,
                WordInfoDatabase::class.java,
                "word_db"
            )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(
        repository: WordInfoRepository
    ): GetWordInfo {
        return GetWordInfo(repository)
    }
}
