package com.axppress.hundredblessings.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.axppress.hundredblessings.domain.local.converters.StringListConverter

@Database(entities = [/*ToWatchEntity::class, FavoriteEntity::class*/], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
//    abstract val movies: MoviesDao
}
