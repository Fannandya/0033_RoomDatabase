package com.example.roomdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Database Room yang memuat tabel Siswa
@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase() {
    // Memberikan objek DAO yang diimplementasikan otomatis oleh Room.
    abstract fun siswaDao(): SiswaDao

    companion object {
        // Volatile memastikan thread lain melihat instance yang terbaru.
        @Volatile
        private var Instance: DatabaseSiswa? = null

        // semua pemanggil menggunakan satu fungsi untuk mendapatkan instance database.
        fun getDatabase(context: Context): DatabaseSiswa {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseSiswa::class.java,
                    "siswa_database"
                ).build().also { Instance = it }
            }
        }
    }
}
