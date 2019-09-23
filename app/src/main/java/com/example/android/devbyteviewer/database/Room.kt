

package com.example.android.devbyteviewer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DictionaryDao {
    @Query("select * from databasedictionary")
    fun getDictionaries(): LiveData<List<DatabaseDictionary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dictionaries: DatabaseDictionary)
}

@Database(entities = [DatabaseDictionary::class], version = 2)
abstract class DictionariesDatabase : RoomDatabase() {
    abstract val dictionaryDao: DictionaryDao
}

private lateinit var INSTANCE: DictionariesDatabase

fun getDatabase(context: Context): DictionariesDatabase {

    synchronized(DictionariesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DictionariesDatabase::class.java, "Dictionary")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    return INSTANCE
}