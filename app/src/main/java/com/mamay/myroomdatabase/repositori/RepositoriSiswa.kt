package com.mamay.myroomdatabase.repositori

import com.mamay.myroomdatabase.model.Siswa
import com.mamay.myroomdatabase.model.SiswaDao
import kotlinx.coroutines.flow.Flow

// Abstraksi sumber data, agar ViewModel tidak perlu berinteraksi langsung dengan DAO.
interface RepositoriSiswa {
    // fungsi untuk mengambil daftar semua siswa dari database
    fun getAllSiswaStream(): Flow<List<Siswa>>

    // fungsi untuk menambah satu siswa ke database secara asinkronus
    suspend fun insertSiswa(siswa: Siswa)

    // fungsi get siswa by id
    fun getSiswaStream(id: Int): Flow<Siswa?>

    // fungsi delete siswa
    suspend fun deleteSiswa(siswa: Siswa)

    // fungsi update siswa
    suspend fun updateSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
) : RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id) // fungsi untuk override get siswa by data
}
