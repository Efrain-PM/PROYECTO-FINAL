package com.example.proyectofinal.Components



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.sensitiveContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.ui.theme.DarkText
import com.example.proyectofinal.ui.theme.YellowPrimary
import com.example.proyectofinal.ui.theme.iosevkaFamily
import com.example.proyectofinal.ui.theme.odibeFamily

import org.intellij.lang.annotations.JdkConstants
//agregue esto para reutilizarlo pero al final ni lo use xd
@Composable
fun CardViewHome(title: String,description:String, buttonText:String,image:Int){//al usar enteros es porque usamos id)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary//color de fondo
        )//se va a expander dependiendo del ancho
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),//ve su tamaño en base a sus hijos
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(//---------------------------------
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.50f)


            ) {//++++++++++++++++++++++++
                Text(
                    text= title,
                    fontSize=20.sp,
                    fontWeight= FontWeight.Bold,
                    color = DarkText //COLOR DEL TEXTO
                )
                spaceV(8)
                Text(
                    text= description,
                    fontSize= 20.sp,
                    color= DarkText //COLOR DEL TEXTO
                )
                spaceV(12)
                ElevatedButton(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text= buttonText,
                        color= DarkText
                    )
                }

            }//++++++++++++++++++++
            Image(
                painter = painterResource(id= image),
                contentDescription = "placeholder",
                modifier = Modifier
                    .height(120.dp)
                    .weight(0.50f),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}
@Composable
fun cardCategoryHome(
    category:String,image:Int,color:Color
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.wrapContentHeight()
            .wrapContentWidth(),
        colors= CardDefaults.cardColors(
            containerColor = YellowPrimary//---------COLOR DE FONDO TARJETA:
        )

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment= Alignment.CenterHorizontally
        ) {
            Image(painter=painterResource(id=image),
                contentDescription= "placeholder",
                modifier= Modifier.height(56.dp),
                contentScale = ContentScale.Fit)
            spaceV(8)
            Text(
                text=category,
                fontWeight = FontWeight.Bold,
                color= DarkText,
                fontSize = 12.sp,
                fontFamily= iosevkaFamily
            )
        }

    }
}


@Composable
fun spaceV(space: Int){
    Spacer(
        Modifier.height(space.dp)
    )
}

@Composable
fun spaceH(space:Int){
    Spacer(
        Modifier.width(space.dp)
    )
}
@Preview
@Composable
fun cardViewPreview(){
//    CardViewHome(
//        "frutas y verduras frescas",
//        "20% de descuento en tu primera compra",
//        stringResource(R.string.texto_boton),
//        R.drawable.gato
    //   )
    cardCategoryHome(
        "Lacteos",R.drawable.ic_launcher_foreground, MaterialTheme.colorScheme.secondary
    )
}