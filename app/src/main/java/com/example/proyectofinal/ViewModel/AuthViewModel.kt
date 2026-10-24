package com.example.proyectofinal.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>("")
    var isLoading by mutableStateOf(false)

    //FUNCION INICIO DE SESION ----------------------
    fun login(onSuccess:()->Unit){
        if(email.isEmpty() or password.isEmpty()){
            errorMessage= "Porfavor completa los campos"
            return
        }
        isLoading=true
        errorMessage=null

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                    task->
                isLoading=false
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    var e = task.exception
                    errorMessage= when (e){
                        is FirebaseAuthException ->  {
                            when(e.errorCode){
                                "ERROR_INVALID_EMAIL"->"El formato de correo es invalido"
                                "ERROR_WRONG_PASSWORD"->"El formato de contraseña es invalido"
                                "ERROR_USER_NOT_FOUND"->"El usuario no fue encontrado"
                                else->"Error de autenticacion:${e.message}"
                            }
                        }
                        else->"Ocurrio un error inesperado"
                    }
                }
            }
    }
    fun clearError(){
        errorMessage=null
    }
    fun logout(onLogout:()->Unit){
        auth.signOut()
        onLogout()
    }
    fun register(onSuccess: () -> Unit){
        if(email.isEmpty() || password.isEmpty()){
            errorMessage = "Por favor, completa los campos"
            return
        }
        isLoading=true
        errorMessage=null

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                    task ->
                isLoading = false
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    val e = task.exception
                    errorMessage = when(e){
                        is FirebaseAuthException -> {
                            when (e.errorCode){
                                "ERROR_INVALID_EMAIL" -> "El formato del correo no es válido"
                                "ERROR_WEAK_PASSWORD" -> "La contraseña es muy débil (debe contener 6 caracteres o más)"
                                "ERROR_EMAIL_ALREADY_IN_USE" -> "Este correo ya está registrado"
                                else -> "Error al registrar: ${e.message}"
                            }
                        }
                        else -> "Ocurrió un error inesperado"
                    }
                }
            }


    }
}