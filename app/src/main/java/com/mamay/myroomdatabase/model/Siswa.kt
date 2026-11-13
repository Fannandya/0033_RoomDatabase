package com.example.roomdb.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity mewakili satu baris pada tabel tblSiswa di database SQLite
@Entity(tableName = "tblSiswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID otomatis dibuat Room/SQLite saat insert.
    val nama: String,
    val alamat: String,
    val telpon: String
)
