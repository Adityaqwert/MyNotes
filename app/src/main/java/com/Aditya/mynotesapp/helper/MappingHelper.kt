package com.Aditya.mynotesapp.helper

import android.database.Cursor
import com.Aditya.mynotesapp.db.DatabaseContract.NoteColumns.Companion.DATE
import com.Aditya.mynotesapp.db.DatabaseContract.NoteColumns.Companion.DESCRIPTION
import com.Aditya.mynotesapp.db.DatabaseContract.NoteColumns.Companion.TITLE
import com.Aditya.mynotesapp.db.DatabaseContract.NoteColumns.Companion._ID
import com.Aditya.mynotesapp.entity.Note

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val title = getString(getColumnIndexOrThrow(TITLE))
                val description = getString(getColumnIndexOrThrow(DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DATE))
                noteList.add(Note(id, title, description, date))
            }
        }
        return noteList
    }
}