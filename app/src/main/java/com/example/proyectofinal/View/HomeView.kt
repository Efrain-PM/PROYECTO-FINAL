package com.example.proyectofinal.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Components.MainScaffold
import com.example.proyectofinal.Components.spaceV
import com.example.proyectofinal.ViewModel.HomeViewModel
import com.example.proyectofinal.ViewModel.MovementsViewModel
import com.example.proyectofinal.data.model.Product
import com.example.proyectofinal.ui.theme.BlueFancy
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.PROYECTOFINALTheme
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    onNavigateToPlus: () -> Unit,
    onNavigateToAll: () -> Unit = {},
    homeViewModel: HomeViewModel = viewModel(),
    movementsViewModel: MovementsViewModel = viewModel()
) {
    val movementsList by movementsViewModel.movements.collectAsState()
    val productsList by homeViewModel.quickProducts.collectAsState()
    val context = LocalContext.current

    val realBalance = movementsList.fold(0f) { acc, movement ->
        if (movement.type == "SUMA") acc + movement.amount
        else if (movement.type == "RESTA") acc - movement.amount
        else acc
    }

    MainScaffold(
        selectedItem = 0,
        onItemSelected = onItemSelected,
        showBottomBar = true
    ) { padding ->
        ContentHomeView(
            modifier = modifier.padding(padding),
            onNavigateToPlus = onNavigateToPlus,
            onNavigateToAll = onNavigateToAll,
            totalBalance = realBalance,
            products = productsList,
            onProductClick = { product ->
                homeViewModel.onProductClicked(product) { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
fun ContentHomeView(
    modifier: Modifier = Modifier,
    onNavigateToPlus: () -> Unit,
    onNavigateToAll: () -> Unit,
    totalBalance: Float,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Box(
        modifier = modifier
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
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "INICIO",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = odibeFamily,
                    color = DarkText
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                spaceV(20)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkBackground)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "SALDO TOTAL",
                            fontSize = 14.sp,
                            fontFamily = iosevkaFamily,
                            fontWeight = FontWeight.Bold,
                            color = BlueFancy// TEXTO DEL SALDOOO--------------
                        )
                        spaceV(4)
                        Text(
                            text = "$ %.2f".format(totalBalance),
                            fontSize = 32.sp,
                            fontFamily = odibeFamily,
                            fontWeight = FontWeight.Bold,
                            color = BlueFancy
                        )
                    }
                }

                spaceV(24)

                SectionHeader("ACCESOS RÁPIDOS", onSeeAllClick = onNavigateToAll)
                spaceV(12)

                if (products.isEmpty()) {
                    Text(
                        text = "No hay accesos rápidos. Crea uno desde +",
                        fontFamily = iosevkaFamily,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(products) { product ->
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 110.dp)
                                    .clickable { onProductClick(product) },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = YellowPrimary)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = product.name,
                                        fontFamily = iosevkaFamily,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkText,
                                        maxLines = 2
                                    )
                                    Text(
                                        text = "$ %.2f".format(product.price),
                                        fontFamily = odibeFamily,
                                        fontSize = 18.sp,
                                        color = DarkText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onNavigateToPlus,
            containerColor = YellowPrimary,
            contentColor = DarkText,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar operación")
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontFamily = iosevkaFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = "ver todas",
                color = YellowPrimary,
                fontFamily = iosevkaFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    PROYECTOFINALTheme {
        HomeView(
            onItemSelected = {},
            onNavigateToPlus = {}
        )
    }
}