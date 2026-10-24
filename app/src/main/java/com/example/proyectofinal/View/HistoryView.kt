package com.example.proyectofinal.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Components.MainScaffold
import com.example.proyectofinal.Components.spaceV
import com.example.proyectofinal.ViewModel.MovementsViewModel
import com.example.proyectofinal.data.model.Movement
import com.example.proyectofinal.ui.theme.BlueFancy
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.PROYECTOFINALTheme
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily

val ExpenseRed = Color(0xFFE53935)

@Composable
fun HistoryView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    movementsViewModel: MovementsViewModel = viewModel()
) {
    val movementsList by movementsViewModel.movements.collectAsState()

    MainScaffold(
        selectedItem = 1,
        onItemSelected = onItemSelected,
        showBottomBar = true
    ) { padding ->
        ContentHistoryView(
            modifier = modifier.padding(padding),
            movements = movementsList,
            onDeleteMovement = { movement ->
                movementsViewModel.deleteMovement(movement)
            }
        )
    }
}

@Composable
fun ContentHistoryView(
    modifier: Modifier = Modifier,
    movements: List<Movement>,
    onDeleteMovement: (Movement) -> Unit
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
                    .background(YellowPrimary)//color del BANER
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "MOVIMIENTOS",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = odibeFamily,
                    color = DarkText
                )
            }

            if (movements.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay movimientos registrados",
                        fontFamily = iosevkaFamily,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(movements, key = { it.id }) { movement ->
                        val isIncome = movement.type == "SUMA"
                        val amountText = if (isIncome) "+ $%.2f".format(movement.amount) else "- $%.2f".format(movement.amount)
                        val amountColor = if (isIncome) BlueFancy else ExpenseRed

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF333333)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = movement.title,
                                        fontFamily = iosevkaFamily,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    spaceV(4)
                                    Text(
                                        text = movement.date,
                                        fontFamily = iosevkaFamily,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }

                                Text(
                                    text = amountText,
                                    fontFamily = odibeFamily,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = amountColor,
                                    modifier = Modifier.padding(end = 8.dp)
                                )

                                IconButton(
                                    onClick = { onDeleteMovement(movement) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar movimiento",
                                        tint = Color.Gray
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

@Preview(showBackground = true)
@Composable
fun HistoryViewPreview() {
    PROYECTOFINALTheme {
        ContentHistoryView(
            movements = listOf(
                Movement(1, "Venta de Filtro", 150f, "SUMA", "12 Oct 2026"),
                Movement(2, "Compra de Bujía", 85f, "RESTA", "11 Oct 2026")
            ),
            onDeleteMovement = {}
        )
    }
}