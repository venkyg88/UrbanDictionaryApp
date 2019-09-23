
package com.example.android.devbyteviewer.domain


/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */

/**
 * Dictionaries represent their meaning and other related information.
 */
data class Dictionary(val defid: Int,
                 val definition: String,
                 val permalink: String,
                 val thumbs_up: Int,
                 val author: String,
                 val word: String,
                 val current_vote: String,
                 val written_on: String,
                 val example: String,
                 val thumbs_down: Int)

