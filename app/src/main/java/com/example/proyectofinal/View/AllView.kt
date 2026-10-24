package com.example.proyectofinal.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Components.spaceV
import com.example.proyectofinal.ViewModel.HomeViewModel
import com.example.proyectofinal.data.model.Product
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily

@Composable
fun AllView(
    onBackClick: () -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val productsList by homeViewModel.quickProducts.collectAsState()
    val context = LocalContext.current

    AllViewContent(
        products = productsList,
        onBackClick = onBackClick,
        onProductClick = { product ->
            homeViewModel.onProductClicked(product) { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        },
        onDeleteProduct = { product ->
            homeViewModel.deleteProduct(product)
            Toast.makeText(context, "Acceso rápido eliminado", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun AllViewContent(
    products: List<Product>,
    onBackClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onDeleteProduct: (Product) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Icon(
            imageVector = Icons.Rounded.Settings,
            contentDescription = "imagen engranaje",
            tint = Color(0xFFB1A34C).copy(alpha = 0.1f),
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(YellowPrimary)
                    .padding(vertical = 12.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = DarkText
                    )
                }

                Text(
                    text = "ACCESOS RÁPIDOS",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = odibeFamily,
                    color = DarkText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (products.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay accesos rápidos registrados",
                        fontFamily = iosevkaFamily,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products, key = { it.id }) { product ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clickable { onProductClick(product) },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = YellowPrimary)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier.align(Alignment.TopStart).fillMaxWidth(0.8f)
                                ) {
                                    Text(
                                        text = product.name,
                                        fontFamily = iosevkaFamily,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkText,
                                        maxLines = 2
                                    )
                                    spaceV(4)
                                    Text(
                                        text = "$ %.2f".format(product.price),
                                        fontFamily = odibeFamily,
                                        fontSize = 18.sp,
                                        color = DarkText
                                    )
                                }

                                IconButton(
                                    onClick = { onDeleteProduct(product) },
                                    modifier = Modifier
                                        .size(28.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar acceso rápido",
                                        tint = Color(0xFFC62828)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}