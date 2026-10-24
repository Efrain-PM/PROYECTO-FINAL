package com.example.proyectofinal.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.GrayInputBackground
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily

@Composable
fun BottonMenu(
    //indica el elemento seleccionado
    selectedItem: Int,
    //recibir funcion que sera ejecutada cuando el usuario seleccione  una opcion
    onItemSelected:(Int) -> Unit
){
    NavigationBar(
        containerColor = YellowPrimary
    ){
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
            },
            icon={
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "inicio"
                )
            },
            label = {
                Text("inicio", fontFamily= iosevkaFamily, fontWeight= FontWeight.Bold)
            },//COLORES---------------
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowPrimary,
                selectedTextColor = DarkBackground,
                unselectedIconColor = DarkBackground,
                unselectedTextColor = DarkBackground,
                indicatorColor = DarkBackground
            )
        )

        NavigationBarItem(
            selected=selectedItem == 2,//PERFIL
            onClick={
                onItemSelected(2)
            },
            icon= {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text("Perfil",fontFamily= iosevkaFamily, fontWeight= FontWeight.Bold)
            },//COLORES
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowPrimary,
                selectedTextColor = DarkBackground,
                unselectedIconColor = DarkBackground,
                unselectedTextColor = DarkBackground,
                indicatorColor = DarkBackground
            )
        )
        NavigationBarItem(
            selected=selectedItem == 1,//HISTORIAL
            onClick={
                onItemSelected(1)
            },
            icon= {
                Icon(
                    imageVector = Icons.Default.AccessTimeFilled,
                    contentDescription = "Historial"
                )
            },
            label = {
                Text("Historial",fontFamily= iosevkaFamily, fontWeight= FontWeight.Bold)
            },//COLORES
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowPrimary,
                selectedTextColor = DarkBackground,
                unselectedIconColor = DarkBackground,
                unselectedTextColor = DarkBackground,
                indicatorColor = DarkBackground
            )
        )
        NavigationBarItem(
            selected=selectedItem == 3,//MAPA
            onClick={
                onItemSelected(3)
            },
            icon= {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "Mapa"
                )
            },
            label = {
                Text("Mapa",fontFamily= iosevkaFamily, fontWeight= FontWeight.Bold)
            },//COLORES
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowPrimary,
                selectedTextColor = DarkBackground,
                unselectedIconColor = DarkBackground,
                unselectedTextColor = DarkBackground,
                indicatorColor = DarkBackground
            )
        )
    }
}