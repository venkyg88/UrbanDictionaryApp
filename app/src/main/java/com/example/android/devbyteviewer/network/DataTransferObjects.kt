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

package com.example.android.devbyteviewer.network

import com.example.android.devbyteviewer.database.DatabaseDictionary
import com.example.android.devbyteviewer.domain.Dictionary
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */

/**
 * VideoHolder holds a list of Videos.
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "dictionaries": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkDictionaryContainer(val list: List<NetworkVideo>)

/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(val defid: Int,
                        val definition: String,
                        val permalink: String,
                        val thumbs_up: Int,
                        val sound_urls: List<Any>,
                        val author: String,
                        val word: String,
                        val current_vote: String,
                        val written_on: String,
                        val example: String,
                        val thumbs_down: Int)

/**
 * Convert Network results to database objects
 */
fun NetworkDictionaryContainer.asDomainModel(): List<Dictionary> {
    return list.map {
        Dictionary(defid = it.defid,
                definition = it.definition,
                thumbs_down = it.thumbs_down,
                thumbs_up = it.thumbs_up,
                author = it.author,
                word = it.word,
                current_vote = it.current_vote,
                written_on = it.written_on,
                example = it.example,
                permalink = it.permalink)
    }
}

//create an extension function that converts from data transfer objects to database objects

fun NetworkDictionaryContainer.asDatabaseModel(): Array<DatabaseDictionary> {
    return list.map {
        DatabaseDictionary(defid = it.defid,
                definition = it.definition,
                thumbs_down = it.thumbs_down,
                thumbs_up = it.thumbs_up,
                author = it.author,
                word = it.word,
                current_vote = it.current_vote,
                written_on = it.written_on,
                example = it.example,
                permalink = it.permalink)
    }.toTypedArray()
}
