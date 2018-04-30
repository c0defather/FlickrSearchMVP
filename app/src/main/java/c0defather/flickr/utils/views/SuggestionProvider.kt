package c0defather.flickr.utils.views

import android.content.SearchRecentSuggestionsProvider


/**
 * Created by kuanysh on 4/29/18.
 */
class SuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        val AUTHORITY = "c0defather.SuggestionProvider"
        val MODE = DATABASE_MODE_QUERIES
    }
}