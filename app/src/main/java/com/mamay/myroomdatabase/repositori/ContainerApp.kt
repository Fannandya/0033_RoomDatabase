package com.mamay.myroomdatabase.repositori

import android.content.Context
import com.example.roomdb.room.DatabaseSiswa

interface ContainerApp {
    val repositoriSiswa: RepositoriSiswa
}

/** Menghubungkan database Room, DAO, dan implementasi repository. */
class ContainerDataApp(private val context: Context) : ContainerApp {
    // lazy: repository baru dibuat ketika pertama kali diakses.
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(DatabaseSiswa.getDatabase(context).siswaDao())
    }
}
