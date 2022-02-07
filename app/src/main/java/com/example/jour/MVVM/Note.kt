package com.example.jour.MVVM

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jourTable")
class Note(
    @ColumnInfo(name = "title") val jourTitle:String,
    @ColumnInfo(name = "description") val jourDescription:String,
    @ColumnInfo(name = "date") val jourDate:String,
    //@ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB) val jourImage: ByteArray
) {
    @PrimaryKey(autoGenerate = true)var id=0
}