package com.seenu.minerva2.di

import android.content.Context
import androidx.room.Room
import com.seenu.minerva2.data.MinervaDatabase
import com.seenu.minerva2.data.goalStats.GoalStatsDao
import com.seenu.minerva2.data.goals.GoalDao
import com.seenu.minerva2.data.tasks.TaskDao
import com.seenu.minerva2.data.userDetails.UserDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMinveraDatabase(@ApplicationContext context: Context):MinervaDatabase{
        return Room.databaseBuilder(
            context, MinervaDatabase::class.java,"MinervaDB"
        ).build()
    }
    @Provides
    fun provideGoalDao(minervaDatabase: MinervaDatabase) : GoalDao{
        return minervaDatabase.goalDao()
    }

    @Provides
    fun provideTaskDao(minervaDatabase: MinervaDatabase) : TaskDao {
        return minervaDatabase.taskDao()
    }

    @Provides
    fun provideUserDetailsDao(minervaDatabase: MinervaDatabase) : UserDetailsDao {
        return minervaDatabase.userDetailsDao()
    }

    @Provides
    fun provideGoalStatsDao(minervaDatabase: MinervaDatabase) : GoalStatsDao {
        return minervaDatabase.goalStatsDao()
    }

}