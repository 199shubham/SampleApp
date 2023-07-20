package com.aasan.sample.di


import android.content.Context
import androidx.room.Room
import com.aasan.sample.dao.SampleDao
import com.aasan.sample.dao.SampleDatabase
import com.aasan.sample.leaderly.domain.LeadRlyUseCase
import com.aasan.sample.leaderly.domain.LeadRlyUseCaseImpl
import com.aasan.sample.leaderly.repository.LeadRlyRepository
import com.aasan.sample.splash.domain.SplashUseCase
import com.aasan.sample.splash.domain.SplashUseCaseImpl
import com.aasan.sample.splash.repository.SampleDataRepository
import com.globalmed.corelib.repository.SharedPreferenceRepository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SMModule {
    @Provides
    fun provideSampleRepository(retrofit: Retrofit) = retrofit.create(SampleDataRepository::class.java)


    @Provides
    @Singleton
    fun providesNoteDatabase(@ApplicationContext context: Context):SampleDatabase{
        return Room.databaseBuilder(context,SampleDatabase::class.java,SampleDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: SampleDatabase):SampleDao{
        return appDatabase.getNoteDao()
    }

    @Provides
    fun provideSampleUseCase(repository: SampleDataRepository,sharedPreferenceRepository: SharedPreferenceRepository,sampleDao: SampleDao):SplashUseCase
       = SplashUseCaseImpl(repository,sharedPreferenceRepository,sampleDao)

    @Provides
    fun provideLeadRlyRepository(retrofit: Retrofit) = retrofit.create(LeadRlyRepository::class.java)


    @Provides
    fun provideLeadRlyUseCase(repository: LeadRlyRepository): LeadRlyUseCase
            = LeadRlyUseCaseImpl(repository)


}