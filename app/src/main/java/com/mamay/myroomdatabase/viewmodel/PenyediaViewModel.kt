package com.mamay.myroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mamay.myroomdatabase.AplikasiSiswa

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer khusus HomeViewModel.
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
        // Initializer khusus EntryViewModel.
        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

// fungsi extension untuk memperoleh objek Application dan mengembalikan instance AplikasiSiswa.
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
