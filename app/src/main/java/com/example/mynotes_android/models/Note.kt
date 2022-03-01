package com.example.mynotes_android.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notesTable")
class Note (@ColumnInfo(name = "title") val noteTitle: String,
            @ColumnInfo(name = "description") val description: String,
            @ColumnInfo(name = "timestamp") val timeStamp: String): Serializable {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}