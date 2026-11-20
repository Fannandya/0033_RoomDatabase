package com.mamay.myroomdatabase.viewmodel.provider

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mamay.myroomdatabase.AplikasiSiswa
import com.mamay.myroomdatabase.viewmodel.DetailViesModel
import com.mamay.myroomdatabase.viewmodel.EditViewModel
import com.mamay.myroomdatabase.viewmodel.EntryViewModel
import com.mamay.myroomdatabase.viewmodel.HomeViewModel

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

        // Initializer khusus DetailViewModel.
        initializer {
            DetailViesModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }

        // Initializer khusus EditViewModel.
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }

    }
}

// fungsi extension untuk memperoleh objek Application dan mengembalikan instance AplikasiSiswa.
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
