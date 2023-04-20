package com.example.provadm.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Sala")
data class SalaEntity(

    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "Nome") val nome: String?,
    @ColumnInfo(name = "perfil") val perfil: String,
    @ColumnInfo(name = "professor") val prof: Boolean
)