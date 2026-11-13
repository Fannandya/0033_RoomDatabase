package com.mamay.myroomdatabase.repositori

import com.example.roomdb.room.Siswa
import com.example.roomdb.room.SiswaDao
import kotlinx.coroutines.flow.Flow

// Abstraksi sumber data, agar ViewModel tidak perlu berinteraksi langsung dengan DAO.
interface RepositoriSiswa {
    // fungsi untuk mengambil daftar siswa dari database
    fun getAllSiswaStream(): Flow<List<Siswa>>

    // fungsi untuk menambah satu siswa ke database secara asinkronus
    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(private val siswaDao: SiswaDao) : RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
}
