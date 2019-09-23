

package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.Dictionary

@Entity
data class DatabaseDictionary constructor(
        @PrimaryKey
        val defid: Int,
        val definition: String,
        val permalink: String,
        val thumbs_up: Int,
        val author: String,
        val word: String,
        val current_vote: String,
        val written_on: String,
        val example: String,
        val thumbs_down: Int)

//Extension function which converts from database objects to domain objects
fun List<DatabaseDictionary>.asDomainModel(): List<Dictionary> {
    return map {
        Dictionary (
                defid = it.defid,
                definition = it.definition,
                permalink = it.permalink,
                thumbs_up = it.thumbs_up,
                thumbs_down = it.thumbs_down,
                author = it.author,
                word = it.word,
                current_vote = it.current_vote,
                written_on =it.written_on,
                example = it.example
                )
    }
}