package com.example.jour.MVVM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jourTable")
class Note(
    @ColumnInfo(name = "title")val jourTitle:String,
    @ColumnInfo(name = "description")val jourDescription:String,
    @ColumnInfo(name= "date")val jourDate:String
) {
    @PrimaryKey(autoGenerate = true)var id=0
}