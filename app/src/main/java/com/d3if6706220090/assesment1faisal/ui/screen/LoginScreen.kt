package com.d3if6706220090.assesment1faisal.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.d3if6706220090.assesment1faisal.Navigator.Screen
import com.d3if6706220090.assesment1faisal.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            actions = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.Home.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(id = R.string.tentang_aplikasi)
                    )
                }
            }
        )

        Column(
            modifier = Modifier.weight(1f), // Menggunakan weight agar Column memenuhi sisa ruang
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.masuk)
                    .replace("<center>", "")
                    .replace("</center>", "\n"),
                modifier = Modifier.padding(horizontal = 16.dp), // Atur margin horizontal di sini
                textAlign = TextAlign.Center, // Pusatkan teks
            )

            Spacer(modifier = Modifier.height(16.dp)) // Berikan jarak antara teks dan tombol

            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text("Masuk")
            }
        }
    }
}











