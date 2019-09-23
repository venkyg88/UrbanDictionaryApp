/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.Video

@Entity
data class DatabaseVideo constructor(
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
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video (
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