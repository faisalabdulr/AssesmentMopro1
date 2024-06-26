package com.d3if6706220090.assesment1faisal.ui.screen

import androidx.lifecycle.ViewModel
import com.d3if6706220090.assesment1faisal.R
import com.d3if6706220090.assesment1faisal.model.MenuItem

class MainViewModel : ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<MenuItem> {
        val data = mutableListOf<MenuItem>()
        val namaToko = arrayOf("WDP", "Pujasera", "Sri Pandita", "Asabil", "JRC", "Alwi", "Podomoro", "Kolab", "Mc donal", "KFC")
        val makanan = arrayOf("Nasi Goreng", "Mie Goreng", "Soto Ayam", "Gado-Gado", "Rendang", "Bakso", "Sate Ayam", "Ayam Goreng", "Pecel Lele", "Capcay")


        for (i in 1..10) {
            val namaToko = namaToko.random()
            val nimRandom = (10000..300000).random().toString()
            val kelasRandom = (10..50).random().toString()
            val makananRandom = makanan.random()
            val hargaRandom = (10000..500000).random()
            data.add(
                MenuItem(
                    id = i.toLong(),
                    nama = namaToko,
                    nim = "RP  " + nimRandom,
                    kelas = "Makan Disini ada diskon" + kelasRandom + "%",
                    Makanan = makananRandom,
                    price = hargaRandom
                )
            )
        }
        return data
    }
}
