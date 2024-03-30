package com.d3if6706220090.assesment1faisal.Navigator

sealed class Screen(val  route: String) {
    data object Login:Screen("Login")
    data object Menu:Screen("Menu")
    data object Home:Screen("Home")
    data object About:Screen("About")
}