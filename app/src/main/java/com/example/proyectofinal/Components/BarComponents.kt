package com.example.proyectofinal.Components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.ui.theme.DarkBackground

@Composable
fun Title(text:String){
    Text(text=text, fontSize=25.sp,color= MaterialTheme.colorScheme.primary, fontWeight= FontWeight.Bold)
}
@Composable
fun MainScaffold(
    selectedItem:Int,
    onItemSelected:(Int)->Unit,
    topBar : @Composable ()-> Unit={},
    floatingActionButton : @Composable ()-> Unit={},
    showBottomBar: Boolean=true,
    content: @Composable (PaddingValues)-> Unit
){
    Scaffold(
        containerColor = DarkBackground,
        topBar=topBar,
        bottomBar = {
            if(showBottomBar){
                BottonMenu(
                    selectedItem = selectedItem,
                    onItemSelected=onItemSelected
                )
            }

        },
        floatingActionButton = floatingActionButton,
        content = content
    )
}