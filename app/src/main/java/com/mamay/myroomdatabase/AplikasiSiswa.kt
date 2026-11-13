package com.mamay.myroomdatabase

import android.app.Application
import com.mamay.myroomdatabase.repositori.ContainerApp
import com.mamay.myroomdatabase.repositori.ContainerDataApp

class AplikasiSiswa: Application(){
    lateinit var container : ContainerApp
    override fun onCreate(){
        super.onCreate()
        container = ContainerDataApp(this)
    }
}