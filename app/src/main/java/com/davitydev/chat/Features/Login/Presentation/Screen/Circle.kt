package com.davitydev.chat.Features.Login.Presentation.Screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.primaryContainerDarkMediumContrast
@Composable
fun Circle(tamanio: Int, x: Int, y:Int, alpha:Float){
    Canvas(modifier = Modifier.size(tamanio.dp)
        .offset(x = (x).dp).offset(y = (y).dp).alpha(alpha)) {
        drawCircle(
            color = primaryContainerDarkMediumContrast,
            radius = size.minDimension / 2
        )
    }
}