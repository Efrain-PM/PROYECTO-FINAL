package com.example.proyectofinal.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Components.spaceV
import com.example.proyectofinal.ViewModel.HomeViewModel
import com.example.proyectofinal.ViewModel.MovementsViewModel
import com.example.proyectofinal.ui.theme.BlueFancy
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily

val RedButtonColor = Color(0xFFFF5252)

@Composable
fun PlusView(
    onBackClick: () -> Unit,
    movementsViewModel: MovementsViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }


    val context = LocalContext.current

    PlusViewContent(
        title = title,
        onTitleChange = { title = it },
        amount = amount,
        onAmountChange = { amount = it },
        onBackClick = onBackClick,
        onAddIncome = {
            movementsViewModel.addMovement(title, amount, "SUMA") {
                Toast.makeText(context, "¡Suma agregada con éxito!", Toast.LENGTH_SHORT).show()
                onBackClick()
            }
        },
        onAddExpense = {
            movementsViewModel.addMovement(title, amount, "RESTA") {
                Toast.makeText(context, "¡Resta agregada con éxito!", Toast.LENGTH_SHORT).show()
                onBackClick()
            }
        },
        onAddProduct = {
            homeViewModel.addQuickProduct(title, amount) {
                Toast.makeText(context, "¡Acceso rápido creado con éxito!", Toast.LENGTH_SHORT).show()
                onBackClick()
            }
        }
    )
}

@Composable
fun PlusViewContent(
    title: String,
    onTitleChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onAddIncome: () -> Unit,
    onAddExpense: () -> Unit,
    onAddProduct: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Icon(
            imageVector = Icons.Rounded.Settings,
            contentDescription = "Fondo engranaje",
            tint = Color(0xFFB1A34C).copy(alpha = 0.1f),
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = YellowPrimary
                    )
                }

                spaceV(8)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(YellowPrimary)
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CREAR",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = odibeFamily,
                        color = DarkText
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    placeholder = {
                        Text(text = "Título", fontFamily = iosevkaFamily, color = Color.Gray, fontSize = 20.sp)
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFD9D9D9),
                        unfocusedContainerColor = Color(0xFFD9D9D9),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = DarkText,
                        unfocusedTextColor = DarkText
                    ),
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.size(60.dp).background(YellowPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$", fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = odibeFamily, color = DarkText)
                    }

                    OutlinedTextField(
                        value = amount,
                        onValueChange = onAmountChange,
                        placeholder = {
                            Text(text = "3000", fontFamily = odibeFamily, color = Color.Gray, fontSize = 32.sp)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = YellowPrimary,
                            unfocusedTextColor = YellowPrimary
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = odibeFamily,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.weight(1f).height(65.dp)
                    )
                }

                spaceV(8)
            //botones xd
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = onAddIncome,
                            modifier = Modifier.weight(1f).height(52.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BlueFancy)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                Icon(Icons.Default.Add, contentDescription = "Suma", tint = DarkText, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Sumar", fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = iosevkaFamily, color = DarkText)
                            }
                        }

                        Button(
                            onClick = onAddExpense,
                            modifier = Modifier.weight(1f).height(52.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = RedButtonColor)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                Icon(Icons.Default.Remove, contentDescription = "Resta", tint = DarkText, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Restar", fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = iosevkaFamily, color = DarkText)
                            }
                        }
                    }

                    Button(
                        onClick = onAddProduct,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = YellowPrimary)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Icon(Icons.Default.FlashOn, contentDescription = "Acceso Rápido", tint = DarkText, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Acceso Rápido", fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = iosevkaFamily, color = DarkText)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(30.dp).background(YellowPrimary)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlusViewPreview() {
    PlusViewContent(
        title = "",
        onTitleChange = {},
        amount = "",
        onAmountChange = {},
        onBackClick = {},
        onAddIncome = {},
        onAddExpense = {},
        onAddProduct = {}
    )
}