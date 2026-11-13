package com.mamay.myroomdatabase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roomdb.room.Siswa
import com.example.roomdb.repositori.RepositoriSiswa

// Memegang data formulir dan logika penyimpanan di halaman entry
class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {
    // mutableStateOf membuat Compose menggambar ulang UI saat nilainya berubah.
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set // UI hanya boleh mengubah state lewat fungsi milik ViewModel.

    // Benar jika semua kolom wajib sudah diisi.
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // Memperbarui isi formulir sekaligus status validasinya.
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    // Menyimpan data valid ke Room melalui repository; suspend tidak memblokir UI.
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

// State untuk tampilan form siswa.
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

// Model nilai input sebelum dikonversi ke entity database.
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// Mengubah data form menjadi entity Room untuk operasi insert.
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

// Mengubah entity database menjadi state UI.
fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

// Menyalin kolom dari entity database ke model form.
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)
