

package com.example.android.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.devbyteviewer.database.DictionariesDatabase
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.Dictionary
import com.example.android.devbyteviewer.network.Network
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DictionariesRepository(private val database: DictionariesDatabase) {

    val dictionaryList: LiveData<List<Dictionary>> = Transformations.map(database.dictionaryDao.getDictionaries()) {
        it.asDomainModel()
    }
    //TODO: pass the parameter form the search button from the Main screen
    //Repository for fetching objects from network and storing them on disk
    suspend fun refreshDictionareis() {
        withContext(Dispatchers.IO){//force the Kotlin coroutine to switch to the IO dispatcher
        val playlist = Network.devbytes.getPlaylist("Venkat").await()
        database.dictionaryDao.insertAll(*playlist.asDatabaseModel())

        }
    }

    val dictionaryListAOrder: LiveData<List<Dictionary>> = Transformations.map(database.dictionaryDao.getUpVotesInAscendingOrder()) {
        it.asDomainModel()
    }

    val dictionaryListDOrder: LiveData<List<Dictionary>> = Transformations.map(database.dictionaryDao.getDownVotesInAscendingOrder()) {
        it.asDomainModel()
    }

}
