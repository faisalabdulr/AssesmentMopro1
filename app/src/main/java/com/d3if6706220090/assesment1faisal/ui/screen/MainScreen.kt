package com.d3if6706220090.assesment1faisal.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.d3if6706220090.assesment1faisal.Navigator.Screen
import com.d3if6706220090.assesment1faisal.R
import com.d3if6706220090.assesment1faisal.model.Profil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.kembali),
                        tint = MaterialTheme.colorScheme.primary)
                }
            },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ), actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){
            padding -> BuatContent(Modifier.padding(padding))
    }
}

@Composable
fun BuatContent(modifier : Modifier){
    var nilaiMakanan by rememberSaveable { mutableStateOf("") }
    var nilaiMakananError by rememberSaveable { mutableStateOf(false) }

    var jumlahPesan by rememberSaveable { mutableStateOf("") }
    var jumlahPesanError by rememberSaveable { mutableStateOf(false) }

    var totalHarga by rememberSaveable { mutableFloatStateOf(0f) }
    var kategori by rememberSaveable { mutableStateOf(0) }

    val radioOptions = listOf(
        stringResource(id = R.string.dine_in),
        stringResource(id = R.string.bawa_Pulang)
    )
    var jenisPesan by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var kondisi by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Nilai Makanan
        OutlinedTextField(
            value = nilaiMakanan, onValueChange = { nilaiMakanan = it },
            label = { Text(text = stringResource(id = R.string.makanan))},
            isError = nilaiMakananError,
            trailingIcon = { IconPicker(nilaiMakananError)},
            supportingText = { ErrorHint(nilaiMakananError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        // Jumlah Pesan
        OutlinedTextField(
            value = jumlahPesan, onValueChange = { jumlahPesan = it },
            label = { Text(text = stringResource(id = R.string.jumlah_pesan))},
            isError = jumlahPesanError,
            trailingIcon = { IconPicker(jumlahPesanError)},
            supportingText = { ErrorHint(jumlahPesanError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        // Radio Button untuk Jenis Pesan (Dine-In atau Takeaway)
        Row {
            radioOptions.forEach{ text ->
                jenisPesanOption(
                    label = text,
                    isSelected = jenisPesan == text,
                    modifier = Modifier
                        .selectable(
                            selected = jenisPesan == text,
                            onClick = { jenisPesan = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Button(
            onClick = {
                // Validasi input
                val hargaMakanan: Float? = nilaiMakanan.toFloatOrNull()

                val jumlahPesanInt: Int? = jumlahPesan.toIntOrNull()

                if (hargaMakanan == null || jumlahPesanInt == null || jumlahPesanInt <= 0) {
                    nilaiMakananError = true
                    jumlahPesanError = true
                    return@Button
                }


                kondisi = true
                totalHarga = hitungTotal(hargaMakanan, jumlahPesanInt)
                kategori = getKategoriPembelian(totalHarga)
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.hitung))
        }

        if (kondisi == true){
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.total_harga, totalHarga),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(kategori).uppercase(),
                style = MaterialTheme.typography.headlineLarge
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,
                            nilaiMakanan, jumlahPesan, totalHarga, context.getString(kategori).uppercase())

                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean){
    if (isError){
        Icon(imageVector =Icons.Filled.Warning, contentDescription = null)
    } else {
        // Tidak melakukan apa-apa jika tidak ada kesalahan
    }
}
@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text = stringResource(id = R.string.input_invalid))
    }
}
@Composable
fun jenisPesanOption(label: String, isSelected: Boolean, modifier: Modifier){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun hitungTotal(hargaMakanan: Float, jumlahPesan: Int): Float {
    return hargaMakanan * jumlahPesan
}


private fun getKategoriPembelian(totalHarga: Float): Int {
    return if (totalHarga > 500000) {
        R.string.diskon_besar
    } else if (totalHarga > 200000) {
        R.string.diskon_sedang
    } else {
        R.string.tanpa_diskon
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

