package com.example.proyectofinal.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Components.spaceV
import com.example.proyectofinal.ViewModel.AuthViewModel
import com.example.proyectofinal.ui.theme.DarkBackground
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.GrayInputBackground
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily


@Composable
fun LoginView(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    authVM: AuthViewModel = viewModel()
) {
    LoginViewContent(
        email = authVM.email,
        password = authVM.password,
        errorMessage = authVM.errorMessage,
        isLoading = authVM.isLoading,
        onEmailChange = { authVM.email = it },
        onPasswordChange = { authVM.password = it },
        onLogin = {
            authVM.login {
                onLoginSuccess()
            }
        },
        onCleanError = { authVM.clearError() },
        onNavigateToRegister = onNavigateToRegister
    )
}

@Composable
fun LoginViewContent(
    email: String,
    password: String,
    errorMessage: String?,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onCleanError: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground) // Fondo
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .background(YellowPrimary)
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "BIENVENIDO",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = odibeFamily,
                    color = DarkText,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = {
                        Text(
                            text = "CORREO ELECTRONICO",
                            fontFamily = iosevkaFamily,
                            fontSize = 14.sp,
                            color = DarkText.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = CircleShape,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = GrayInputBackground,
                        unfocusedContainerColor = GrayInputBackground,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = DarkText,
                        unfocusedTextColor = DarkText
                    )
                )

                spaceV(16)

                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    placeholder = {
                        Text(
                            text = "CONTRASEÑA",
                            fontFamily = iosevkaFamily,
                            fontSize = 14.sp,
                            color = DarkText.copy(alpha = 0.7f)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = CircleShape,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = GrayInputBackground,
                        unfocusedContainerColor = GrayInputBackground,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = DarkText,
                        unfocusedTextColor = DarkText
                    )
                )

                errorMessage?.let { error ->
                    spaceV(8)
                    Text(
                        text = error,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontFamily = iosevkaFamily,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                spaceV(16)

                Button(
                    onClick = onLogin,
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = YellowPrimary,
                        contentColor = DarkText
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = DarkText,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "INICIAR SESION",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = iosevkaFamily
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¿No tienes una cuenta?",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = iosevkaFamily
                )
                TextButton(
                    onClick = {
                        onCleanError()
                        onNavigateToRegister()
                    }
                ) {
                    Text(
                        text = "Regístrate",
                        color = YellowPrimary,
                        fontSize = 16.sp,
                        fontFamily = iosevkaFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    LoginViewContent(
        email = "",
        password = "",
        errorMessage = null,
        isLoading = false,
        onEmailChange = {},
        onPasswordChange = {},
        onLogin = {},
        onCleanError = {},
        onNavigateToRegister = {}
    )
}